# Empon App
Android App for My Final Project at University of Surabaya

## Main Features
- Android App
  - Showing available information of empon-empon data.
  - Predict an empon-empon from gallery / photo.
- Flask App
  - Deployed in a server for handling image request and sending response data. I used [this](https://www.rosehosting.com/blog/how-to-deploy-flask-application-with-nginx-and-gunicorn-on-ubuntu-20-04/) and [this  as reference](https://www.digitalocean.com/community/tutorials/how-to-serve-flask-applications-with-gunicorn-and-nginx-on-ubuntu-20-04) for deploying the Flask application online.
  - Inference model used: modified `vgg16` pretrained PyTorch model.

## Usage
### Android App
- You can find the Android Studio project in [android_empon_ta](https://github.com/owenlieyanto/empon_app/tree/master/android_empon_ta) folder.
- If the server is not online, you need to change the url in `DetectFragment.kt` on line 81:

```
val url = "http://10.0.2.2:5000/predict"
```

### Flask App
- Flask project can be found in [flask_empon_ta](https://github.com/owenlieyanto/empon_app/tree/master/flask_empon_ta) folder.
- To run Flask project as a server, you need to:
	1. Install Python.
	2. Activate virtual environment.
	3. Set the environment to `development`.
	4. Run Flask.
- To test the server, you need to:
	1. Change directory to `..\flask_empon_ta\test`.
	2. Run the `test.py`.
	3. Don't forget to change the url to `http://10.0.2.2:5000/predict`.
