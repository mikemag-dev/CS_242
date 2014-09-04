/** @file alloc.c */
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>



typedef struct node node;
struct node
{

	node* prev;
	size_t data_size;
	char is_free;
	node* next;
	void* ptr;

};

node* mem_start = NULL;
node* mem_end = NULL;

/**
 * Allocate space for array in memory
 * 
 * Allocates a block of memory for an array of num elements, each of them size
 * bytes long, and initializes all its bits to zero. The effective result is
 * the allocation of an zero-initialized memory block of (num * size) bytes.
 * 
 * @param num
 *    Number of elements to be allocated.
 * @param size
 *    Size of elements.
 *
 * @return
 *    A pointer to the memory block allocated by the function.
 *
 *    The type of this pointer is always void*, which can be cast to the
 *    desired type of data pointer in order to be dereferenceable.
 *
 *    If the function failed to allocate the requested block of memory, a
 *    NULL pointer is returned.
 *
 * @see http://www.cplusplus.com/reference/clibrary/cstdlib/calloc/
 */
void *calloc(size_t num, size_t size)
{
	/* Note: This function is complete. You do not need to modify it. */
	void *ptr = malloc(num * size);
	
	if (ptr)
		memset(ptr, 0x00, num * size);

	return ptr;
}


/**
 * Allocate memory block
 *
 * Allocates a block of size bytes of memory, returning a pointer to the
 * beginning of the block.  The content of the newly allocated block of
 * memory is not initialized, remaining with indeterminate values.
 *
 * @param size
 *    Size of the memory block, in bytes.
 *
 * @return
 *    On success, a pointer to the memory block allocated by the function.
 *
 *    The type of this pointer is always void*, which can be cast to the
 *    desired type of data pointer in order to be dereferenceable.
 *
 *    If the function failed to allocate the requested block of memory,
 *    a null pointer is returned.GSCC
 *
 * @see http://www.cplusplus.com/reference/clibrary/cstdlib/malloc/
 */

void insertAfter(node* ptr, node* to_insert)
{
	if(!ptr || !to_insert)
		return;
	node* temp_next = ptr->next;
	ptr->next = to_insert;
	to_insert->prev = ptr;
	to_insert->next = temp_next;
}

void removeNode(node* ptr)
{
	if(!ptr) return;
	if (!ptr->prev)
	{
		ptr->next->prev = NULL;
		return;
	}
	if (!ptr->next)
	{
		ptr->prev->next = NULL;
		return;
	}
	ptr->prev->next = ptr->next;
	ptr->next->prev = ptr->prev;
	return;
}

//necessary?
void shiftNode(node* n, size_t size)
{
	node* newNode = NULL;
	node* orig_prev = n->prev;
	node* orig_next = n->next;
	void* orig_ptr = n->ptr;
	size_t orig_data_size = n->data_size;
	char orig_is_free = n->is_free;
	n = n + size;
	n->prev = orig_prev;
	if(n->prev) n->prev->next = n;
	n->next = orig_next;
	if(n->next) n->next->prev = n;
	n->ptr = orig_ptr;
	n->data_size = orig_data_size - size;
	n->is_free = orig_is_free;
}

void *malloc(size_t size)
{
	if(size == 0)
	{	
		printf("size equal to 0\n");
		return 0;	
	}

	if(mem_start == NULL)
	{
		printf("Reached %d in %s\n", __LINE__, __FILE__);
		mem_start = mem_end = sbrk(0);
		sbrk(sizeof(node));
		node* return_ptr = sbrk(0);		
		//move over size of data

		if( sbrk(size) == (void*)-1)
		{
			printf("should not reach here, mem was empty and not enough space remaining?\n");
			return 0;
		}
		mem_start->prev = NULL;
		mem_start->data_size = size;
		mem_start->is_free = 'n';
		mem_start->next = NULL;
		mem_start->ptr = return_ptr;
		return return_ptr;
	}
	//mem_start not empty
	else
	{
		node* runner = mem_start;
		//look for free node of acceptable size
		while(runner != NULL)
		{
			//is the current node free and size exactly enough?
			if (runner->is_free == 'y' && runner->data_size == size)
			{
				runner->is_free == 'n';
				runner->data_size = size;
				node* return_ptr = runner + sizeof(node);				
				runner->ptr = return_ptr;
				return return_ptr;
			}
			else if (runner->is_free == 'y' && runner->data_size >= size + sizeof(node))
			{
				runner->is_free == 'n';
				runner->data_size = size;
				node* return_ptr = runner + sizeof(node);				
				runner->ptr = return_ptr;
				return_ptr = return_ptr + sizeof(node);
				node* temp_runner_next = runner->next;
				runner->next = return_ptr + size;
				runner->next->prev = runner;
				runner->next->next = temp_runner_next;
				if(runner->next == NULL)
				{
					int diff = size - runner->data_size;
					if ( diff > 0)
					{
						sbrk(diff);
						if(sbrk(0) == NULL)
						{	
							return NULL;
						}
					}
					else 
					{
						brk(sbrk(0)+diff);
					}
					runner->data_size+=diff;
					return return_ptr;	
				}				
				runner->next->is_free = 'y';
				runner->next->data_size = runner->data_size - sizeof(node) - size;
				runner->next->ptr = runner->next + sizeof(node);
				return return_ptr;
			}
			else
			{
				runner = runner -> next;
			}	
		}
		//no free memory found
		node* to_add = sbrk(0);
		sbrk(sizeof(node));
		node* return_ptr = sbrk(0);		
		//move over size of data
		if( sbrk(size) == (void*)-1)
		{
			printf("should not reach here, mem was empty and not enough space remaining?\n");
			return 0;
		}
		to_add->prev = mem_end;
		mem_end->next = to_add;		
		mem_end = to_add;
		to_add->data_size = size;
		to_add->is_free = 'n';
		to_add->next = NULL;
		to_add->ptr = to_add + sizeof(node);
		return return_ptr;
	}
	return NULL;
}


/**
 * Deallocate space in memory
 * 
 * A block of memory previously allocated using a call to malloc(),
 * calloc() or realloc() is deallocated, making it available again for
 * further allocations.
 *
 * Notice that this function leaves the value of ptr unchanged, hence
 * it still points to the same (now invalid) location, and not to the
 * null pointer.
 *
 * @param ptr
 *    Pointer to a memory block previously allocated with malloc(),
 *    calloc() or realloc() to be deallocated.  If a null pointer is
 *    passed as argument, no action occurs.
 */

void coalesce(node* ptr1, node* ptr2)
{
	if(!ptr1 || !ptr2) return;
	if(ptr1->is_free=='y' && ptr2->is_free=='y')
	{
		ptr1->next = ptr2->next;
		ptr1->data_size = ptr1->data_size+sizeof(node)+ptr2->data_size;
	}
}
void free(void *ptr)
{
		// "If a null pointer is passed as argument, no action occurs."
	if (!ptr)
		return;
	node* runner = mem_start;
	//printf("%p\n",ptr);
	while(runner != NULL)	
	{
		if (runner->ptr == ptr)
		{
			//printf("Reached %d in %s\n", __LINE__, __FILE__);
			runner->is_free = 'y';
			coalesce(runner, runner->next);
			coalesce(runner->prev, runner);
			return;
		}
		runner = runner->next;
	}
	return;
}





/**
 * Reallocate memory block
 *
 * The size of the memory block pointed to by the ptr parameter is changed
 * to the size bytes, expanding or reducing the amount of memory available
 * in the block.
 *
 * The function may move the memory block to a new location, in which case
 * the new location is returned. The content of the memory block is preserved
 * up to the lesser of the new and old sizes, even if the block is moved. If
 * the new size is larger, the value of the newly allocated portion is
 * indeterminate.
 *
 * In case that ptr is NULL, the function behaves exactly as malloc, assigning
 * a new block of size bytes and returning a pointer to the beginning of it.
 *
 * In case that the size is 0, the memory previously allocated in ptr is
 * deallocated as if a call to free was made, and a NULL pointer is returned.
 *
 * @param ptr
 *    Pointer to a memory block previously allocated with malloc(), calloc()
 *    or realloc() to be reallocated.
 *
 *    If this is NULL, a new block is allocated and a pointer to it is
 *    returned by the function.
 *
 * @param size
 *    New size for the memory block, in bytes.
 *
 *    If it is 0 and ptr points to an existing block of memory, the memory
 *    block pointed by ptr is deallocated and a NULL pointer is returned.
 *
 * @return
 *    A pointer to the reallocated memory block, which may be either the
 *    same as the ptr argument or a new location.
 *
 *    The type of this pointer is void*, which can be cast to the desired
 *    type of data pointer in order to be dereferenceable.
 *    
 *    If the function failed to allocate the requested block of memory,
 *    a NULL pointer is returned, and the memory block pointed to by
 *    argument ptr is left unchanged.
 *
 * @see http://www.cplusplus.com/reference/clibrary/cstdlib/realloc/
 */
void *realloc(void *ptr, size_t size)
{
	 // "In case that ptr is NULL, the function behaves exactly as malloc()"
	if (!ptr)
		return malloc(size);

	 // "In case that the size is 0, the memory previously allocated in ptr
	 //  is deallocated as if a call to free() was made, and a NULL pointer
	 //  is returned."
	if (!size)
	{
		free(ptr);
		return NULL;
	}

	//printf("Reached %d in %s\n", __LINE__, __FILE__);
	node* runner = mem_start;
	while(runner != NULL)	
	{	
		//printf("Reached %d in %s\n", __LINE__, __FILE__);
		if(runner->is_free == 'y')
			{
				printf("Reached %d in %s\n", __LINE__, __FILE__);
				return NULL;
			}
		if (runner->ptr == ptr)
		{
			printf("Reached %d in %s\n", __LINE__, __FILE__);
			//same size
			if(runner->data_size == size)
			{
				printf("Reached %d in %s\n", __LINE__, __FILE__);
				return ptr;
			}
			//shrinking
			if(runner->data_size > size)
			{
				size_t diff = runner->data_size - size;
				if(diff>=sizeof(node))
				{
					node* to_insert = runner->ptr + size;
					to_insert->data_size = diff-sizeof(node);
					to_insert->ptr = to_insert+sizeof(node);
					insertAfter(runner, to_insert);
				}
			}
			//expanding
			else
			{
				size_t diff = size - runner->data_size;
				if(runner->next == NULL)
				{
					sbrk(diff);
					if(sbrk(0) == NULL)
					{	
						printf("Reached %d in %s\n", __LINE__, __FILE__);
						return NULL;
					}
					runner->data_size = size;

				}
				//have to move next node
				else if(runner->next->is_free == 'y' && runner->data_size + runner->next->data_size >= size)
				{
					shiftNode(runner->next, diff);
					runner->data_size = size;
				}
				//have to remove next node
				else if(runner->next->is_free == 'y' && runner->data_size + sizeof(node) + runner->next->data_size >= size)
				{
					removeNode(runner->next);
					runner->data_size = size;
				}
				return ptr;							
			}
			runner->is_free = 'y';
			coalesce(runner, runner->next);
			coalesce(runner->prev, runner);
			printf("Reached %d in %s\n", __LINE__, __FILE__);			
			return ptr;
		}
		runner = runner->next;
	}
	return malloc(size);
	printf("Reached %d in %s\n", __LINE__, __FILE__);
	return NULL;
}