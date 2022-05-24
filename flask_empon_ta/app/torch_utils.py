import io
import torch
import torch.nn as nn
import torchvision.transforms as transforms
from PIL import Image

# load model
PATH = "app/empon_vgg16_c9.pth"
model = torch.load(PATH, map_location=torch.device('cpu'))
model.eval()

# image -> tensor
def transform_image(image_bytes):
    transform = transforms.Compose([
        transforms.Resize(225),
        transforms.CenterCrop(224),
        transforms.ToTensor(),
        transforms.Normalize([0.485, 0.456, 0.406], [0.229, 0.224, 0.225])
    ])
    image = Image.open(io.BytesIO(image_bytes))
    return transform(image).unsqueeze(0)

# predict
def get_prediction(image_tensor):
    outputs = model(image_tensor)

    m = nn.Softmax(dim=1)
    outputs = m(outputs)
    _, predicted = torch.max(outputs.data, 1)

    return _, predicted