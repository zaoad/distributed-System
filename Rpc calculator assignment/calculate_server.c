/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */


#include "calculate.h"
float mat1[100][100],mat2[100][100];
//display a matrix given its size
void display(float A[100][100],int r,int c)
{
	for (int i=0; i<r; i++)
	{
		for (int j=0; j<c; j++)
			printf("%f ",A[i][j]);
		printf("\n");
	}
}
//display two input matrix
void printInServer(float A[100][100],int r1, int c1,float B[100][100],int r2,int c2,char operator)
{
    printf("input:\n");
    printf("Operation: %c\n ",operator);
    printf("matrixA:\n");
    printf("-----------------------------\n");
    display(mat1,r1,c1);
    printf("-----------------------------\n");
    printf("matrixA:\n");
    printf("-----------------------------\n");
    display(mat2,r2,c2);
    printf("-----------------------------\n");
}
//adding two matrix from client
output * add_1_svc(inputs *argp, struct svc_req *rqstp)
{
    printf("function call from server\n");
    //initialization
    static output result;
    int cnt1=0,cnt2=0;
    int r1,c1,r2,c2;
    r1=argp->r1;
    c1=argp->c1;
    r2=argp->r2;
    c2=argp->c2;
    //check if dimension of two matrix is same
    if(r1!=r2 || c1!=c2)
    {
        result.flag=0;
        printf("dimension error\n");
        return &result;
    }
    //matrix formation from input
    for(int i=0; i<argp->r1; i++)
    {
        for(int j=0; j<argp->c1; j++)
        {
            mat1[i][j]=argp->num1[cnt1];
            cnt1++;
        }
    }
    for(int i=0; i<argp->r2; i++)
    {
        for(int j=0; j<argp->c2; j++)
        {
            mat2[i][j]=argp->num2[cnt2];
            cnt2++;
        }
    }
    printInServer(mat1,r1,c1,mat2,r2,c2,argp->operator);//print input
    int cnt=0;

    printf("Summation:\n");
    printf("-----------------------------\n");
    //sum operation;
    for(int i=0; i<argp->r1; i++)
    {

        for(int j=0; j<argp->c1; j++)
        {
            float temp=mat1[i][j]+mat2[i][j];
            result.a[cnt++]=temp;
            printf("%f ",temp);
        }
        printf("\n");
    }
    printf("-----------------------------\n");
    printf("sent to client\n");
    printf("\n");
    printf("\n");
    result.flag=1;
    return &result; //return result
}
//subtraction two matrix
output * sub_1_svc(inputs *argp, struct svc_req *rqstp)
{
    //initialization
    printf("function call from client\n");
    static output result;
    int cnt1=0,cnt2=0;
    int r1,r2,c1,c2;
    r1=argp->r1;
    c1=argp->c1;
    r2=argp->r2;
    c2=argp->c2;

    if(r1!=r2 || c1!=c2)//check if dimension of two matrix is same or not
    {
        result.flag=0;
        printf("dimension error\n");
        return &result;
    }

    for(int i=0; i<argp->r1; i++)
    {
        for(int j=0; j<argp->c1; j++)
        {
            mat1[i][j]=argp->num1[cnt1];//matrix formation from input
            cnt1++;
        }
    }
    for(int i=0; i<argp->r2; i++)
    {
        for(int j=0; j<argp->c2; j++)
        {
            mat2[i][j]=argp->num2[cnt2];////matrix formation from input
            cnt2++;
        }
    }
    printInServer(mat1,r1,c1,mat2,r2,c2,argp->operator);//print input
    int cntr=0;
    printf("Subtraction:\n");
    printf("-----------------------------\n");
    for(int i=0; i<argp->r1; i++)
    {
        printf("\n");
        for(int j=0; j<argp->c1; j++)
        {
            float temp=mat1[i][j]-mat2[i][j];//subtraction of two entity of matrixes
            result.a[cntr++]=temp;
            printf("%f ",temp);
        }
    }
    printf("\n");
    printf("-----------------------------\n");
    printf("sent to client\n");
    result.flag=1;
    return &result;
}

output * mul_1_svc(inputs *argp, struct svc_req *rqstp)
{
    printf("function call from client\n");
    static output result;
    int cnt1=0,cnt2=0;
    int r1,c1,r2,c2;
    r1=argp->r1;
    c1=argp->c1;
    r2=argp->r2;
    c2=argp->c2;
    if(c1!=r2)//check if column of first matrix is equal to row of second matrix
    {
        printf("dimension error\n");
        result.flag=0;
        return &result;
    }
    for(int i=0; i<argp->r1; i++)
    {
        for(int j=0; j<argp->c1; j++)
        {
            mat1[i][j]=argp->num1[cnt1];//matrix formation from input
            cnt1++;
        }
    }
    for(int i=0; i<argp->r2; i++)
    {
        for(int j=0; j<argp->c2; j++)
        {
            mat2[i][j]=argp->num2[cnt2];//matrix formation from input
            cnt2++;
        }
    }
    printInServer(mat1,r1,c1,mat2,r2,c2,argp->operator);//print input
    printf("Multiplication:\n");
    printf("-----------------------------\n");
    int cntr=0;
    for (int i = 0; i<r1; i++) {
      for (int j= 0; j< c2; j++) {
        float sum=0;
        for (int k = 0; k <c1 ; k++) {
          sum = sum + mat1[i][k]*mat2[k][j];//matrix multiplication
        }
        result.a[cntr++]=sum;//enter it to reuslt
        printf("%f" ,sum );
      }
      printf("\n");
    }
    printf("-----------------------------\n");
    printf("sent to client\n");
    printf("\n");
    printf("\n");
    result.flag=1;
    return &result;
}
/////function for inverse matrix//////
void cofactors(float mat[100][100], float temp[100][100], int p, int q, int n, int c)
{
	int i = 0, j = 0;
	for (int r = 0; r < n; r++)
	{
		for (int c = 0; c < n; c++)
		{
			if (r != p && c != q)
			{
				temp[i][j] = mat[r][c];
				j=j+1;
				if (j == n - 1)
				{
					j = 0;
					i++;
				}
			}
		}
	}
}

/* Recursive function for finding determinant of matrix.
n is current dimension of A[][]. */
float findDeterminant(float mat[100][100], int n,int c)
{


	// Base case : if matrix contains single element
	if (n == 1)
		return mat[0][0];

    float val = 0; // Initialize result

    int sign = 1; // To store sign multiplier

	float temp[100][100]; // To store cofactors



	// Iterate for each element of first row
	for (int f = 0; f < n; f++)
	{
		// Getting Cofactor of A[0][f]
		cofactors(mat, temp, 0, f, n,c);
		val += sign * mat[0][f] * findDeterminant(temp, n - 1,c);

		// terms are to be added with alternate sign
		sign = -1*sign;
	}

	return val;
}

// Function to get adjoint of A[N][N] in adj[N][N].
void adjoint(float A[100][100],float adj[100][100],int r,int c)
{
	if (r == 1)
	{
		adj[0][0] = 1;
		return;
	}

	// temp is used to store cofactors of A[][]
	int sign = 1;
	float temp[100][100];

	for (int i=0; i<r; i++)
	{
		for (int j=0; j<r; j++)
		{
			// Get cofactor of A[i][j]
			cofactors(A, temp, i, j, r,c);

			// sign of adj[j][i] positive if sum of row
			// and column indexes is even.
			if((i+j)%2==0)
			{
                sign=1;
			}
			else
			{
                sign=-1;
			}

			// Interchanging rows and columns to get the
			// transpose of the cofactor matrix
			adj[j][i] = (sign)*(findDeterminant(temp, r-1,c));
		}
	}
}

// Function to calculate and store inverse, returns false if
// matrix is singular
int findInverse(float A[100][100], float inverse[100][100],int r,int c)
{
	// Find determinant of A[][]
	float adjointmat[100][100];
	float det = findDeterminant(A, r,c);
	if (findDeterminant(A, r,c) == 0)
	{
		printf("Singular matrix, can't find its inverse");
		return 0;
	}

	// Find adjoint
	adjoint(A, adjointmat,r,c);

	// Find Inverse using formula "inverse(A) = adj(A)/det(A)"
	for (int i=0; i<r; i++)
		for (int j=0; j<r; j++)
			inverse[i][j] = adjointmat[i][j]/det;

	return 1;
}
//calculating inverse function
output * inv_1_svc(inputs *argp, struct svc_req *rqstp)
{
    printf("function call from client\n");
    //initialization
    static output result;
    int cnt1=0,cnt2=0;
    int r1,c1;
    r1=argp->r1;
    c1=argp->c1;
    if(r1!=c1)//check if matrix is square matrix
    {
        result.flag=0;
        printf("dimension error\n");

        return &result;
    }
    for(int i=0; i<argp->r1; i++)
    {
        for(int j=0; j<argp->c1; j++)
        {
            mat1[i][j]=argp->num1[cnt1];//matrix formation from input
            cnt1++;
        }
    }
    printf("input:\n");
    printf("operation: inverse\n");
    printf("matrix:\n");
    printf("-----------------------------\n");
    display(mat1,r1,c1);
    printf("-----------------------------\n");
    int cntr=0;

    float inv[100][100];
    if (findInverse(mat1, inv,r1,c1))//call inverese function
    {
        printf("Inverse matrix:\n"); //print inverse matrix
        printf("-----------------------------\n");
        display(inv,r1,r1);
        printf("-----------------------------\n");
        printf("sent to client\n");
        printf("\n");
        printf("\n");
    }
    else{
        result.flag=2;
        printf("diterminant zero\n");
        return &result;
    }
    for(int i=0;i<r1;i++)
    {
        for(int j=0;j<r1;j++)
        {
            result.a[cntr++]=inv[i][j];
        }
    }
    result.flag=1;
    return &result;
}
output * trans_1_svc(inputs *argp, struct svc_req *rqstp)
{
    printf("function call from client\n");
	static output result;
    int cnt1=0,cnt2=0;
    int r1,c1;
    r1=argp->r1;
    c1=argp->c1;
    for(int i=0; i<argp->r1; i++)
    {
        for(int j=0; j<argp->c1; j++)
        {
            mat1[i][j]=argp->num1[cnt1];//matrix formation from input
            cnt1++;
        }
    }
    printf("input:\n");
    printf("operation: Transpose\n");
    printf("matrix:\n");
    printf("-----------------------------\n");
    display(mat1,r1,c1);
    printf("-----------------------------\n");
    int cntr=0;
    float trn[100][100];
    for(int i=0;i<r1;i++)
    {
        for(int j=0;j<c1;j++)
        {
            trn[j][i]=mat1[i][j];
        }
    }
    for(int i=0;i<c1;i++)
    {
        for(int j=0;j<r1;j++)
        {
            result.a[cntr++]=trn[i][j];
        }
    }
    printf("Transpose matrix:\n"); //print inverse matrix
    printf("-----------------------------\n");
    display(trn,c1,r1);
    printf("-----------------------------\n");
    printf("sent to client\n");
    printf("\n");
    printf("\n");
    result.flag=1;
    return &result;
}
