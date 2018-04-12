#ifndef QUICK_SORT_HEADER________________
#define QUICK_SORT_HEADER________________


template<typename T, typename compare = std::less<T>>
void q_sort(T input[], int l_idx, int r_idx, compare comp = compare()) {

	if (l_idx >= r_idx)
		return;

	// The below is the partition block (can be made a sub function)

	int left = l_idx;
	int right = r_idx;
	{
		int pivot_idx = l_idx;
		T pivot = input[pivot_idx];

		while (left < right) {
			while (comp(input[left], pivot))
				left++;
			while (comp(pivot, input[right]))
				right--;
			swap(input[left], input[right]);
		}

		swap(pivot, input[left]);
	}

	q_sort(input, l_idx, left, comp);
	q_sort(input, left + 1, r_idx, comp);

}

template<typename T, typename compare = std::less<T>>
void quick_sort(T array[], int N, compare comp = compare()) {
	// This is an improvisation on the merge sort algorithm
	// is in-place and works on the divide-and-conquer methodology
	// Choose a pivot and find its appropriate place, such that
	// All elements less than the pivot are on its left and all elements
	// greater are on its right. Once found, split the porlblem into subsets
	// of elements less than and greater than the pivot and recursively
	// follow the process.
	q_sort(array, 0, N - 1, comp);

}


#endif