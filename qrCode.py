import qrcode
import numpy as np
import sys
import io
import subprocess
import sys

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
    
    return qr.make_image(fill="black", back_color="white").convert("L")

def qr_code_to_ascii(image):
    np_array = np.array(image)
    ascii_art = ""

    for row in np_array:
        for pixel in row:
            if pixel == 0:
                ascii_art += "  "
            else:
                ascii_art += "██"
        ascii_art += "\n"
    
    return ascii_art

def main():
    link = "https://youtu.be/dQw4w9WgXcQ?si=Ae5Xa3o4akffxKlG"
    qr_image = create_qr_code(link)
    ascii_art = qr_code_to_ascii(qr_image)
    print(ascii_art)

if __name__ == "__main__":
    main()
