void setup() {
  size(1200, 800);
  background(0,0,76);
  //stroke(19,70,159);
  strokeWeight(4);

  for (int i = 0; i < 25; i++) {
    stroke(i*10,i*10,i*10);
    new MDLine(0, 100 + int(pow(i,2.4)), 1200, 100 + int(pow(i,2.4))).mda(5, 25+int(pow(i,1.5)));
  }
}
