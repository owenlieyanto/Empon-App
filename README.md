# Empon App
Android App for My Final Project at University of Surabaya

## Main Features:
- Android App:
  - Showing available information of empon-empon data
  - Predict an empon-empon from gallery / photo
- Flask App:
  - Deployed in a server for handling image request and sending response data. I used [this link](https://www.rosehosting.com/blog/how-to-deploy-flask-application-with-nginx-and-gunicorn-on-ubuntu-20-04/) and [this link](https://www.digitalocean.com/community/tutorials/how-to-serve-flask-applications-with-gunicorn-and-nginx-on-ubuntu-20-04) for my reference deploying the Flask application on the server.
  - Inference Model Used: Modified Pretrained PyTorch Model `vgg16`/`mobilenet_v3_large`
