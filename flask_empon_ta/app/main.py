# Source code based on: 
# https://youtu.be/bA7-DEtYCNM

from flask import Flask, request, jsonify
from app.torch_utils import transform_image, get_prediction

app = Flask(__name__)

ALLOWED_EXTENSIONS = {'png', 'jpeg', 'jpg'}
CLASS_NAMES = ['jahe_emprit', 'jahe_merah', 'jahe_putih', 'kencur', 'kunyit_hitam', 'kunyit_kuning', 'kunyit_putih', 'lengkuas', 'temulawak']

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/predict', methods=['POST'])
def predict():
    if request.method == 'POST':

        file = request.files.get('file')
        if file is None or file.filename == '':
            return jsonify({'error': 'no file'})
        if not allowed_file(file.filename):
            return jsonify({'error': 'format not supported', "filename":file.filename})

        try:  
            img_bytes = file.read()
            tensor = transform_image(img_bytes)
            accuracy, prediction = get_prediction(tensor)
            accuracy = accuracy.detach().numpy()[0]
            prediction = CLASS_NAMES[prediction.item()]
            data = {'predicted_class': prediction, 'accuracy': float(accuracy)}
            return jsonify(data)

        except:
            return jsonify({'error': 'error during prediction'})

    return jsonify({'result': 1})