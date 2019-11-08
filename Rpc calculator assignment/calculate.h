/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _CALCULATE_H_RPCGEN
#define _CALCULATE_H_RPCGEN

#include <rpc/rpc.h>


#ifdef __cplusplus
extern "C" {
#endif


struct inputs {
	float num1[1000];
	float num2[1000];
	int r1;
	int r2;
	int c1;
	int c2;
	char operator;
};
typedef struct inputs inputs;

struct output {
	float a[1000];
	int flag;
};
typedef struct output output;

#define CALCULATE_PROG 0x2fffffff
#define CALCULATE_VER 1

#if defined(__STDC__) || defined(__cplusplus)
#define ADD 1
extern  output * add_1(inputs *, CLIENT *);
extern  output * add_1_svc(inputs *, struct svc_req *);
#define SUB 2
extern  output * sub_1(inputs *, CLIENT *);
extern  output * sub_1_svc(inputs *, struct svc_req *);
#define MUL 3
extern  output * mul_1(inputs *, CLIENT *);
extern  output * mul_1_svc(inputs *, struct svc_req *);
#define INV 4
extern  output * inv_1(inputs *, CLIENT *);
extern  output * inv_1_svc(inputs *, struct svc_req *);
#define TRANS 5
extern  output * trans_1(inputs *, CLIENT *);
extern  output * trans_1_svc(inputs *, struct svc_req *);
extern int calculate_prog_1_freeresult (SVCXPRT *, xdrproc_t, caddr_t);

#else /* K&R C */
#define ADD 1
extern  output * add_1();
extern  output * add_1_svc();
#define SUB 2
extern  output * sub_1();
extern  output * sub_1_svc();
#define MUL 3
extern  output * mul_1();
extern  output * mul_1_svc();
#define INV 4
extern  output * inv_1();
extern  output * inv_1_svc();
#define TRANS 5
extern  output * trans_1();
extern  output * trans_1_svc();
extern int calculate_prog_1_freeresult ();
#endif /* K&R C */

/* the xdr functions */

#if defined(__STDC__) || defined(__cplusplus)
extern  bool_t xdr_inputs (XDR *, inputs*);
extern  bool_t xdr_output (XDR *, output*);

#else /* K&R C */
extern bool_t xdr_inputs ();
extern bool_t xdr_output ();

#endif /* K&R C */

#ifdef __cplusplus
}
#endif

#endif /* !_CALCULATE_H_RPCGEN */