/*
 *
 *  main.c file
 *
 */

#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include "srt.h"






static int compare(const void *, const void *);

int main(int argc, char *argv[]) {

    int nelem = argc == 2 ? atoi(argv[1]) : SHRT_MAX;

    TYPE *a = calloc(nelem, sizeof(TYPE));

#ifdef RAND
    for (int i = 0; i < nelem; ++i) {

        a[i] = (TYPE)rand() / RAND_MAX;
    }
#else
    for (int i = 0; i < nelem; ++i) {

        a[i] = i;
    }
#endif

#if defined BUBB
    srtbubb(a, nelem, sizeof(TYPE), compare);
#elif defined HEAP
    srtheap(a, nelem, sizeof(TYPE), compare);
#elif defined INSR
    srtinsr(a, nelem, sizeof(TYPE), compare);
#elif defined MERG
    srtmerg(a, nelem, sizeof(TYPE), compare);
#else
    qsort(a, nelem, sizeof(TYPE), compare);
#endif

#ifdef PRNT
    for (int i = 0; i < nelem; ++i) {

        printf("%f\n", a[i]);
    }
#else
    for (int i = 0; i < nelem - 1; ++i) {

        if (a[i] > a[i + 1]) {

            printf("fail\n");
            goto end;
        }
    }

    printf("pass\n");
#endif

end:

    free(a);

    return 0;
}

static int compare(const void *p1, const void *p2) {

    if (*(TYPE *)p1 < *(TYPE *)p2) {

        return -5;
    }
    else if (*(TYPE *)p1 > *(TYPE *)p2) {

        return +5;
    }

    return 0;
}
/*
gcc -std=c99 -DRAND -DPRNT -DTYPE=float -DHEAP *.c -o sort_program
to print out the sorted array

gcc -std=c99 -DRAND -DTYPE=float -DHEAP *.c -o sort_program
gcc -std=c99 -DRAND -DTYPE=double -DHEAP *.c -o sort_program
to print out pass or fail

./sort_program 1000
run the program with 1000 elements
*/