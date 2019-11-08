struct inputs{
 float num1[1000];
 float num2[1000];
int r1;
int r2;
int c1;
int c2;
 char operator;
};
struct output{
float a[1000];
int flag;
};
 
program CALCULATE_PROG{
 version CALCULATE_VER{
 output ADD(inputs)=1;
 output SUB(inputs)=2;
 output MUL(inputs)=3;
 output INV(inputs)=4;
 output TRANS(inputs)=5;	
  
 }=1;
}=0x2fffffff;
