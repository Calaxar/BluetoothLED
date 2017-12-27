const byte numChars = 32;
char receivedChars[numChars]; // an array to store the received data

boolean newData = false;

// Define our pins
int redPin = 9;     // Red LED hooked to pwm 9
int greenPin = 10;  // Green LED hooked to pwm 10
int bluePin = 11;   // Blue LED hooked to pwm 11

void setup(void) 
{
  // Set up our pins
  pinMode(redPin, OUTPUT);
  pinMode(greenPin, OUTPUT);
  pinMode(bluePin, OUTPUT);
  
  // Start Serial
  Serial.begin(9600);
  
  // Run our start-up sequence
  bootSequence();
  Serial.println("<Arduino is ready>");
}

void loop() {
  static byte ndx = 0;
  char endMarker = '\n';
  char rc;
  int red = 0;
  int green = 0;
  int blue = 0;
  
  if (Serial.available() > 0) {
    while (Serial.available() > 0 && newData == false) {
      rc = Serial.read(); 
    
      if (rc != endMarker) {
        receivedChars[ndx] = rc;
        ndx++;
        if (ndx >= numChars) {
          ndx = numChars - 1;
        }
      }
      else {
        receivedChars[ndx] = '\0'; // terminate the string
        ndx = 0;
        newData = true;
      }
    }
  }

  if (newData == true) {
    for(int i = 0; i < 3; i++) {
      int c = (int)receivedChars[i] - 48;
      red *= 10;
      red += c;
    }
    setRed(red);

    for(int i = 3; i < 6; i++) {
      int c = (int)receivedChars[i] - 48;
      green *= 10;
      green += c;
    }
    setGreen(green);

    for(int i = 6; i < 9; i++) {
      int c = (int)receivedChars[i] - 48;
      blue *= 10;
      blue += c;
    }
    setBlue(blue);

    Serial.println(receivedChars);
    newData = false;
  }
}

// Serial.read() appears to go much faster than we can send text.
// Therefore, we add a method to do a blocking read.
int getNextByte() {
  while (Serial.available() == 0) {
    // BLOCK
  }
  return Serial.read();
}

// Something to do when the 8BitBox is first powered on
// Just flash some pretty colors :-)
void bootSequence() {
  setColor(255, 0, 0);  // red
  delay(500);
  setColor(0, 255, 0);  // green
  delay(500);
  setColor(0, 0, 255);  // blue
  delay(500);
  setColor(255, 255, 0);  // yellow
  delay(500);  
  setColor(80, 0, 80);  // purple
  delay(500);
  setColor(0, 255, 255);  // aqua
  delay(500);
}

// Set the PWM of the Red LED
void setRed(int val) {
  // Sanitize the value
  if (val > 255) {
    val = 255;
  }
  if (val < 0) {
    val = 0;
  }  
  analogWrite(redPin, val);
}

// Set the PWM of the Green LED
void setGreen(int val) {
  // Sanitize the value
  if (val > 255) {
    val = 255;
  }
  if (val < 0) {
    val = 0;
  }  
  analogWrite(greenPin, val);
}

// Set the PWM of the Blue LED
void setBlue(int val) {
  // Sanitize the value
  if (val > 255) {
    val = 255;
  }
  if (val < 0) {
    val = 0;
  }  
  analogWrite(bluePin, val);
}
  
  
// Set all the colors at once  
void setColor(int red, int green, int blue) {
  setRed(red);
  setGreen(green);
  setBlue(blue); 
}

