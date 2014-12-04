/*
*/
#include<stdio.h>
#include<stdlib.h>

int **P;
int ARRAY_COUNT = 0;

int user_min(int x, int y)
{
   if (x > y)
      return y;
   return x;
}
int Floyd(int n, int **D, int **W, int **P)
{
   int i, j, k;

   for (i = 0; i < n; i++)
   {
      for (j = 0; j < n; j++)
      {
         D[i][j] = W[i][j];
         P[i][j] = 0;
      }
   }
   for (k = 0; k < n; k++)
   {
      for (i = 0; i < n; i++)
      {
         for (j = 0; j < n; j++)
         {
//            printf("i, j %d   %d\n", i, j);
            if (D[i][j] > (D[i][k] + D[k][j]))
            {

               D[i][j] = user_min(D[i][j], D[i][k]+D[k][j]);
               P[i][j] = k + 1;
            }
         }
      }
   }
   return 0;
}

int Path(int q, int r)
{
   q = q - 1;
   r = r - 1;
   if (q < 0 || r < 0 )
   {
      return 0;
   }
   if (P[q][r] != 0)
   {
   Path(q, P[q][r]);
   printf(" %d - ", P[q][r]);
   Path(P[q][r], r);
   }
}

int main()
{
   char user_input[100];
   int ARRAY_COUNT = 0;

   int **D;
   int **W;
   char *ptr;
   int i, j, k;
   int value;
   int s,d;
   //����� �Է� �ޱ�
   printf("[?] Input value >> :");
   scanf("%s", user_input);
   
 

   //�迭�� ũ�� ù��°�� �Է¹޴� ��
   ARRAY_COUNT = user_input[0] - 0x30;
   
   //4���� ũ�� 10���� ������ Ȯ��
   if (user_input[1] != ',' || ARRAY_COUNT < 4)
   {
      printf("[-] Error Checking Array Range!\n");
      exit(0);
   }

   // P ����� 
   // 2���� �迭 ���� �Ҵ� �ϱ�
   P = (int**)malloc(sizeof(int*)* ARRAY_COUNT);
   for (i = 0; i<ARRAY_COUNT; i++){
      P[i] = (int*)malloc(sizeof(int)* ARRAY_COUNT);
   }
   // P �ʱ�ȭ
   for (i = 0; i < ARRAY_COUNT; i++)
   {
      for (j = 0; j < ARRAY_COUNT; j++)
      {
         P[i][j] = 0;
      }
   }

   // D ����� 
   // 2���� �迭 ���� �Ҵ� �ϱ�
   D = (int**)malloc(sizeof(int*)* ARRAY_COUNT);
   for (i = 0; i<ARRAY_COUNT; i++){
      D[i] = (int*)malloc(sizeof(int)* ARRAY_COUNT);
   }

   // W ����� 
   // 2���� �迭 ���� �Ҵ� �ϱ�
   W = (int**)malloc(sizeof(int*)* ARRAY_COUNT);
   for (i = 0; i<ARRAY_COUNT; i++){
      W[i] = (int*)malloc(sizeof(int)* ARRAY_COUNT);
   }

   // �Ľ�
   s= user_input[strlen(user_input)-3]-0x30;
   d= user_input[strlen(user_input)-1]-0x30;

   ptr = strtok(user_input, "\"");
   //while (ptr != NULL){
   for (i = 0; i < ARRAY_COUNT; i++){
      //ptr = strtok(NULL, "\"");
      for (j = 0; j < ARRAY_COUNT-1; j++){
         ptr = strtok(NULL, ",");
         value =  atoi(ptr);
         if (value < 0)
            value = 999999;
         D[i][j] = value;
         W[i][j] = value;
      }
      ptr = strtok(NULL, "\"");
      value =  atoi(ptr);
      if (value < 0)
         value = 999999;
      D[i][j] = value;
      W[i][j] = value;

      ptr = strtok(NULL, "\"");
   }

   Floyd(ARRAY_COUNT, D, W, P);

   printf("\n D\n");
   for (i = 0; i < ARRAY_COUNT; i++){
      for (j = 0; j < ARRAY_COUNT; j++){
         printf("%2d ", D[i][j]);
      }
      printf("\n");
   }

   printf("\n\n P\n");
   for (i = 0; i < ARRAY_COUNT; i++){
      for (j = 0; j < ARRAY_COUNT; j++){
         printf("%2d ", P[i][j]);
      }
      printf("\n");
   }
   
   printf("\n");

   printf(" %d -",s );
   Path(s,d);
   printf(" %d ", d);

   system("pause");
}

