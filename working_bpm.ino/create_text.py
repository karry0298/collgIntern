import serial
port = "/dev/tty.usbmodem1411"

ard = serial.Serial(port,115200,timeout=1)
while(True):
    print(ard.read())
