import sys
import socket 
from datetime import datetime

# defining the target
if len(sys.argv) == 2:
    target = socket.gethostbyname(sys.argv[1]) 
else: 
    print("Add a target hostname or IP")

# The scan info 
print("-" * 25)
print("Scan Target: " + target)
print("Scan start time: " + str(datetime.now()))
print("-" * 25)

# now we will run the scan 
try: 
    for port in range(1, 65536): 
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        socket.setdefaulttimeout(1)
        result = s.connect_ex((target, port))
        if result == 0:
            print(f"Port {port}: Open".format(port))  
        s.close()
except KeyboardInterrupt:
    print("\nExiting program.")
    sys.exit()