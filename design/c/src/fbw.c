#include <stdio.h>
#include <string.h>

#define MAX_RESULT_LEN 100
#define R_SUCCESS 1
#define R_FAILURE 0

typedef int (*pred)(int);
typedef char* (*action)(int);

enum r_type
{
  R_ATOM, R_AND, R_OR
};

struct r_atom
{
  pred p;
  action a;
};

struct r_and
{
  struct rule* rs[2];
};

struct r_or
{
  struct rule* rs[2];
};

struct rule
{
  enum r_type type;
  union
  {
    struct r_atom atom;
    struct r_and and;
    struct r_or or;
  } val;
};

struct rule_result
{
  int type;
  char value[MAX_RESULT_LEN];
};

struct rule* atom(pred p, action a)
{
  struct rule* r;
  struct r_atom atom;

  r = (struct rule*)malloc(sizeof(struct rule));

  atom.p = p;
  atom.a = a;

  r->type = R_ATOM;
  r->val.atom = atom;
  return r;
};

struct rule* and(struct rule* r1, struct rule* r2)
{
  struct rule* and = (struct rule*)malloc(sizeof(struct rule));

  and->type = R_AND;
  and->val.and.rs[0] = r1;
  and->val.and.rs[1] = r2;
  return and;
};

struct rule* or(struct rule* r1, struct rule* r2)
{
  struct rule* or = (struct rule*)malloc(sizeof(struct rule));

  or->type = R_OR;
  or->val.or.rs[0] = r1;
  or->val.or.rs[1] = r2;
  return or;
};

struct rule_result apply(struct rule* r, int N);

struct rule_result apply_atom(struct r_atom r, int N)
{
  // FIXME
  struct rule_result rr = { R_FAILURE, { 0 } };
  if (r.p(N)) {
    //printf("%s\n", "atom success");
    rr.type = R_SUCCESS;
    strcpy(rr.value, r.a(N));
  }
  else {
    //printf("%s\n", "atom failure");
    rr.type = R_FAILURE;
  }

  //printf("atom-->[%s]\n", rr.value);
  return rr;
};

struct rule_result apply_and(struct r_and r, int N)
{
  int i;
  char val[100] = { 0 };
  struct rule_result rr = { R_FAILURE, { 0 } };

  for (i = 0; i < 2; i++) {
    rr = apply(r.rs[i], N);
    if (rr.type == R_FAILURE) {
      //printf("%s\n", "and success");
      return rr;
    }
    else {
      //printf("%s\n", "and failure");
      strcat(val, rr.value);
    }
  }
  rr.type = R_SUCCESS;
  strcpy(rr.value, val);

  //printf("and-->[%s]\n", rr.value);
  return rr;
};

struct rule_result apply_or(struct r_or r, int N)
{
  int i;
  struct rule_result rr = { R_FAILURE, { 0 } };

  for (i = 0; i < 2; i++) {
    //printf("%i ", i);
    rr = apply(r.rs[i], N);
    if (rr.type == R_SUCCESS) {
      //printf("%s\n", "or success");
      return rr;
    }
    //printf("%s\n", "or failure");
  }

  //printf("or-->[%s]\n", rr.value);
  return rr;
};

struct rule_result apply(struct rule* r, int N)
{
  struct rule_result rr;
  switch (r->type) {
    case R_ATOM:
    //printf("ATOM\n");
      rr = apply_atom(r->val.atom, N);
      break;
    case R_AND:
      rr = apply_and(r->val.and, N);
      break;
    case R_OR:
      //printf("OR\n");
      rr = apply_or(r->val.or, N);
      break;
    default:
      //assert(0);
      break;
  }
  return rr;
};

int times_3(int N)
{
  return N % 3 == 0;
};

int times_5(int N)
{
  return N % 5 == 0;
};

int times_7(int N)
{
  return N % 7 == 0;
};

struct rule* and2(struct rule* r1, struct rule* r2)
{
  return and(r1, r2);
};

struct rule* and3(struct rule* r1, struct rule* r2, struct rule* r3)
{
  return and(r1, and(r2, r3));
};

struct rule* or3(struct rule* r1, struct rule* r2, struct rule* r3)
{
  return or(r1, or(r2, r3));
};

struct rule* or4(struct rule* r1, struct rule* r2, struct rule* r3, struct rule* r4)
{
  return or(r1, or(r2, or(r3, r4)));
};

char* to_fizz()
{
  return "Fizz";
};

char* to_buzz()
{
  return "Buzz";
};

char* to_whizz()
{
  return "Whizz";
};

char* nope()
{
  return "";
};

int contains_3(int N)
{
  // FIXME
  return 0 == 1;
};

int always_true(int N)
{
  return 0 == 0;
};

int main()
{
  int i;

  printf("hello,world!\n");

  struct rule* r1_3 = atom(times_3, to_fizz);
  struct rule* r1_5 = atom(times_5, to_buzz);
  struct rule* r1_7 = atom(times_7, to_whizz);

  struct rule* r1 = or3(r1_3, r1_5, r1_7);
  struct rule* r2 = or4(and3(r1_3, r1_5, r1_7),
                        and2(r1_3, r1_5),
                        and2(r1_5, r1_7),
                        and2(r1_3, r1_7));

  struct rule* r3 = atom(contains_3, to_fizz);
  struct rule* rd = atom(always_true, nope);

  struct rule* spec = or4(r3, r2, r1, rd);

  for (i = 1; i <= 100; i++) {
    struct rule_result rr = apply(spec, i);
    printf("*[%3i][%s]\n", i, rr.value);  
  }
  
}
