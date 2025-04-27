import { Application } from 'express';
import axios from 'axios';
import express from 'express'; 

export default function (app: Application): void {
  app.get('/', async (req, res) => {
    try {
      const response = await axios.get('http://localhost:4000/tasks');
      console.log(response.data);
      res.render('home', { tasks: response.data });
    } catch (error) {
      console.error('Error fetching tasks:', error);
      res.render('home', { tasks: [] });
    }
  });

app.post('/create-task', express.urlencoded({ extended: true }), async (req, res, next) => {
  try {
    const { title, description, status, dueDate } = req.body;
    await axios.post('http://localhost:4000/tasks', {
      title,
      description,
      status,
      dueDate
    });

    res.redirect('/');
  } catch (error) {
    console.error('Error creating task:', error);
    next(error); // Forward to the centralized error handler
  }
});

  app.post('/delete-task/:id', async (req, res, next) => {
  const { id } = req.params;
  try {
    await axios.delete(`http://localhost:4000/tasks/${id}`);
    res.redirect('/');
  } catch (error) {
    console.error('Error deleting task:', error);
    next(error);
  }
});

  app.post('/update-status/:id', express.urlencoded({ extended: true }), async (req, res, next) => {
  const { id } = req.params;
  const { status } = req.body;
  try {
        await axios.put(`http://localhost:4000/tasks/${id}/status`, status, {
        headers: { 'Content-Type': 'text/plain' }
      });
    res.redirect('/');
  } catch (error) {
    console.error('Error updating task status:', error);
    next(error);
  }
});

}


