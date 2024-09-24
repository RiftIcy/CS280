/*
 *
 *  srtheap.c file
 *
 */

#include <stdbool.h>
#include <stddef.h>
#include "srt.h"

void heapify(void *array, size_t n, size_t size, int (*compare)(const void *, const void *), size_t i) {
    size_t largest = i;
    size_t left = 2 * i + 1;
    size_t right = 2 * i + 2;
    char *arr = (char *)array;

    if (left < n && compare(arr + left * size, arr + largest * size) > 0) {
        largest = left;
    }
    if (right < n && compare(arr + right * size, arr + largest * size) > 0) {
        largest = right;
    }
    if (largest != i) {
        swap(arr + i * size, arr + largest * size, size);
        heapify(array, n, size, compare, largest);
    }
}

void srtheap(void *array, size_t n, size_t size, int (*compare)(const void *, const void *)) {
    for (size_t i = n / 2 - 1; i < n; i--) {
        heapify(array, n, size, compare, i);
    }
    for (size_t i = n - 1; i > 0; i--) {
        swap((char *)array, (char *)array + i * size, size);
        heapify(array, i, size, compare, 0);
    }
}


