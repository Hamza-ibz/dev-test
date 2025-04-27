import { app } from '../../main/app';
import request from 'supertest';
import axios from 'axios';
import { expect } from 'chai';

// Mock axios
jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

describe('Task Management Frontend', () => {
  beforeEach(() => {
    jest.resetAllMocks(); // Reset mocks before each test
  });

  it('should GET the home page and display tasks', async () => {
    mockedAxios.get.mockResolvedValueOnce({
      data: [
        {
          id: 1,
          title: 'Test Task 1',
          description: 'Description 1',
          status: 'Pending',
          dueDate: '2025-05-01T10:00'
        }
      ]
    });

    const res = await request(app)
      .get('/')
      .expect(200);

    expect(res.text).to.include('Test Task 1');
    expect(res.text).to.include('Description 1');
    expect(res.text).to.include('Pending');
  });

  it('should POST and create a new task', async () => {
    mockedAxios.post.mockResolvedValueOnce({ status: 201 });

    const taskData = {
      title: 'New Task',
      description: 'New Task Description',
      status: 'Pending',
      dueDate: '2025-05-02T14:00'
    };

    const res = await request(app)
      .post('/create-task')
      .type('form')
      .send(taskData)
      .expect(302);

    expect(res.headers.location).to.equal('/');
  });

  it('should POST and delete a task', async () => {
    mockedAxios.delete.mockResolvedValueOnce({ status: 204 });

    const res = await request(app)
      .post('/delete-task/1')
      .expect(302);

    expect(res.headers.location).to.equal('/');
  });

  it('should POST and update a task status', async () => {
    mockedAxios.put.mockResolvedValueOnce({ status: 200 });

    const res = await request(app)
      .post('/update-status/1')
      .type('form')
      .send({ status: 'Completed' })
      .expect(302);

    expect(res.headers.location).to.equal('/');
  });
});
