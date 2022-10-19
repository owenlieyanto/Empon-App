# Empon App

Android App for My Final Project at University of Surabaya

## Main Features

- Android App
  - Showing available information of empon-empon data.
  - Predict an empon-empon from gallery / photo.
- Flask App
  - Deployed in a server for handling image request and sending response data. I used [this](https://www.rosehosting.com/blog/how-to-deploy-flask-application-with-nginx-and-gunicorn-on-ubuntu-20-04/) and [this ](https://www.digitalocean.com/community/tutorials/how-to-serve-flask-applications-with-gunicorn-and-nginx-on-ubuntu-20-04)as reference for deploying the Flask application online.
  - Inference model used: modified `vgg16` pretrained PyTorch model.

## Dataset & Model

I've uploaded the raw dataset & the model on [Kaggle](https://www.kaggle.com/datasets/owenlie/empon-dataset).
