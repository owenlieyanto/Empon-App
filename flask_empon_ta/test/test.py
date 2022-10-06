import requests

''' SERVER UBAYA '''
# resp = requests.post("http://143.198.192.71:5000/predict", files={'file': open('temulawak.jpg', 'rb')})

''' SERVER LOKAL ''' 
resp = requests.post("http://localhost:5000/predict", files={'file': open('temulawak.jpg', 'rb')})

print(resp.text)