int const SIDE_LENGTH = 4;

int xOut [SIDE_LENGTH];
int yOut [SIDE_LENGTH];
int zOut [SIDE_LENGTH];

void setup() {
}

void loop() {
  
}

//x = row; y = layer; z = row select
void set(int x, int y, int z) {
  
  //quick error check
  if(x>4 || y>4 || z>4 || x<0 || y<0 || z<0)
    return;
  
  //set values!
  for(int i = 0; i < SIDE_LENGTH; i++) {
    xOut[i] = x%2;
    yOut[i] = y%2;
    zOut[i] = z%2;
    x /= 2;
    y /= 2;
    z /= 2;
  }
}
