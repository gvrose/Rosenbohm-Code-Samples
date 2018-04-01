
/*
 * Author: 1828799
 * Program: C Shell
 * Date: 26 February 2015
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h> 

#define MAX_BUFFSIZE 1024

int main(int argc, char* argv[])
{
    /* Variable declarations */
    char* command = NULL;
    char* commandArgs[256]; /* Max number */
    int childPid = 0;
	char commandBuffer[MAX_BUFFSIZE];
	char* currentDirectory; /* The current directory */
	char* previousDirectory; /* The previous directory */
	int status = 0; /* Status of environment change */
	int ampersand = 0; /* Set the ampersand to "false" */
	int indexCounter = 0; /* The counter for indexing */
	

    while(1) /* Repeat forever */
    {
        commandBuffer[0] = '\0'; /* "Empty" commandBuffer */
		
		childPid = 0; /* Reset the childPid to 0 */
		
		indexCounter = 0; /* Reset the counter to 0 */
		
		ampersand = 0; /* Reset the ampersand to false */
		
		
		/* Prompt for command until one is received */
		while (strlen(commandBuffer) < 2)	
		{
			/* Display a prompt */
			printf("%% ");
			
			/* Read a command from the user */
			if (!fgets(commandBuffer, MAX_BUFFSIZE, stdin))
			{
				return 0;  /* Exit, something didn't work */
			}
		}
		
		
		/* Remove the carriage return at end of command buffer and 
		 * replace it with the null character 
		 */
		commandBuffer[strlen(commandBuffer) - 1] = '\0';
		
	
		/* Parse the string into the command portion and
         * an array of pointers to argument strings using the blank
         * character as the separator. 
         */
		command = strtok(commandBuffer, " ");
		while (command)
		{
			commandArgs[indexCounter] = command; /* Add argument to commandArgs */
			indexCounter++; /* Increment the index counter */
			command = strtok(NULL, " "); /* Get the next piece of the command */
		}
		 
		
		/* Add null pointer to end of array */
		commandArgs[indexCounter] = NULL;
		
		
		/* If the user entered 'exit' then call the exit() system call
         * to terminate the process
         */
		if (!strcmp(commandArgs[0], "exit"))
		{
			exit(0);
		}
		 
		
		/* Before forking a process check for the ampersand (&) sign and
		 * remove it from the command arguments
		 */
		if (!strcmp(commandArgs[indexCounter - 1], "&"))
		{
			/* Set the flag for an ampersand to true */
			ampersand = 1; 
			 
			/* Remove the ampersand for later use of command */
			commandArgs[indexCounter - 1] = NULL;
		}
		

        /* If user entered in a "cd" command perform the internal process for
		 * that command
		 */
		if (!strcmp(commandArgs[0], "cd"))
		{	
			currentDirectory = getcwd(NULL, 0);
			if (commandArgs[1] == NULL || !strcmp(commandArgs[1], "~"))
			{
				/* Go to home directory */
				status = chdir(getenv("HOME"));
			}
			else if (!strcmp(commandArgs[1], "-"))
			{
				/* Go to previous directory */
				status = chdir(previousDirectory);
			}
			else /* Change the directory to the specified place */
			{
				status = chdir(commandArgs[1]);
			}
			
			/* If directory was not successfully changed */
			if (-1 == status)
			{
				fprintf(stderr, "Error: %s\n", strerror(errno));
			}
			else if (-1 == setenv("PWD", getcwd(NULL, 0), 0)) /* Update PWD */
			{
				/* If setting PWD fails somehow display message */
				fprintf(stderr, "Error: %s\n", strerror(errno));
			}
			else
			{
				/* All pieces of directory change has worked correctly so
				 * update the previous directory 
				 */
				 
				 previousDirectory = currentDirectory;
			}
			
		}
		else /* Else fork child to perform command */
		{
			/* Fork a child process to execute the command and return 
			 * the result of the fork() in the childPid variable so 
			 * we know whether we're now executing as the parent 
			 * or child and whether or not the fork() succeeded
			 */
			
			childPid = fork();
			
			if (!childPid) /* We forked no child, we ARE the child */
			{ 
				/* We're now executing in the context of the child process.
				 * Use execvp() to replace this program in the process' memory
				 * with the executable command the user has asked for.  
				 */
			
				execvp(commandArgs[0], commandArgs);
				
				/* If child reaches this we know that the execvp failed */
				perror("Error: ");
				exit(errno);
			}
			else if (-1 == childPid) 
			{
				/* An error occurred during the fork - print it */
				fprintf(stderr, "Error: %s\n", strerror(errno));
			}
			else /* childPid is the PID of the child */
			{
				/* Executing as the parent now, wait for child process if
				 * the process is not supposed to be run in the background 
				 *(no "&")
				 */
				if(!ampersand) 
				{
					waitpid(-1, NULL, 0);
				}
				else
				{
					/* NOTE: Due to the parent not waiting, output gets messed
					 * up and currently no solution has been found other than
					 * to have the parent process sleep for a short period of 
					 * time before continuing. However below that is not done,
					 * so they output continues to potentially messed up after
					 * a child is forked for a process that runs in the 
					 * background.
					 */
					printf("Job %d\n", childPid);
				}
				
			} /* if-else block */
			
		} /* if-else block */
		
	} /* while */

} /* my shell */