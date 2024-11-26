import qrcode
import numpy as np
import sys
import io
import subprocess
import os

sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')

def install_and_import(package):
    try:
        __import__(package)
    except ImportError:
        subprocess.check_call([sys.executable, "-m", "pip", "install", package])
        
libraries = ['qrcode', 'numpy', 'sys', 'io', 'subprocess']

for library in libraries:
    install_and_import(library)


def create_qr_code(link):
    qr = qrcode.QRCode(
        version=1,
        error_correction=qrcode.constants.ERROR_CORRECT_L,
        box_size=1,
        border=1,
    )
    qr.add_data(link)
    qr.make(fit=True)
    
    return qr.make_image(fill="black", back_color="white")


def save_qr_image(image, filepath):
    image.save(filepath)


def main():
    link = "https://youtu.be/dQw4w9WgXcQ?si=Ae5Xa3o4akffxKlG"
    qr_image = create_qr_code(link)
    
    # Path tempat QR code akan disimpan
    output_path = "D:\\PBO\\UAS\\project-pbo-kelompok-3\\QR\\qr_image.png"
    save_qr_image(qr_image, output_path)
    
    print(f"QR Code saved to: {output_path}")

if __name__ == "__main__":
    main()
