import { app } from '../../main/app';
import { expect } from 'chai';
import request from 'supertest';
import axios from 'axios';

// Mock axios so we don't need real backend
jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

describe('Task Management Frontend Routes', () => {
  beforeEach(() => {
    jest.resetAllMocks();
  });

  it('should GET / and show tasks', async () => {
    mockedAxios.get.mockResolvedValueOnce({
      data: [
        {
          id: 1,
          title: 'Test Task',
          description: 'Test description',
          status: 'Pending',
          dueDate: '2025-05-01T10:00'
        }
      ]
    });

    const res = await request(app)
      .get('/')
      .expect(200);

    expect(res.text).to.include('Test Task');
    expect(res.text).to.include('Test description');
    expect(res.text).to.include('Pending');
  });

  it('should POST /create-task and create a new task', async () => {
    mockedAxios.post.mockResolvedValueOnce({ status: 201 });

    const taskData = {
      title: 'New Task',
      description: 'New task description',
      status: 'Pending',
      dueDate: '2025-05-02T15:00'
    };

    const res = await request(app)
      .post('/create-task')
      .type('form')
      .send(taskData)
      .expect(302);

    expect(res.headers.location).to.equal('/');
  });

  it('should POST /delete-task/:id and delete a task', async () => {
    mockedAxios.delete.mockResolvedValueOnce({ status: 204 });

    const res = await request(app)
      .post('/delete-task/1')
      .expect(302);

    expect(res.headers.location).to.equal('/');
  });

  it('should POST /update-status/:id and update task status', async () => {
    mockedAxios.put.mockResolvedValueOnce({ status: 200 });

    const res = await request(app)
      .post('/update-status/1')
      .type('form')
      .send({ status: 'Completed' })
      .expect(302);

    expect(res.headers.location).to.equal('/');
  });
});
