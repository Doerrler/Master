#Git Cheat Sheet

1. **pull-** Retrieves files from the remote repository and then merges it with the local repository. This command is useful for ensuring the repository on your machine is up to date, especially if multiple people are working on the same project.
2. **status-** Shows the status of local files and compares them to the working directory. Any files that are only in the local directory (untracked), that are modified but not updated in the remote repository (modified), and that are added and ready to be commit (staged) are all listed.
3. **add-** Adds files which are modified in the working directory to the index. This makes the files staged and ready to commit.
4. **commit-** Generates a new commit object which points to the index with the written changes and then sets the branch to point to the new commit. Don't forget to have a commit message indicating what changes were made. Use: git commit -a -m "Commit Message Here"
5. **push-** Adds all the modified local objects (most importantly any commit objects) to the remote repository and advances its branches. This should be done after commiting changes.
6. **clone-** Copies the Git repository from a remote source and adds the original location so files can be pulled and pushed to it. Useful for personal computers but probably wont be used too frequently for the class.
7. **remote-** Shows all remote versions of the repoistory. This can be helpful to keep track of the repository if there are several versions for a group project.
8. **config-** Configures values for the user name, email, file formats, and more options. Since we configured everything during the first lab this probably isn't too applicable for most assignements.

(* TA COMMENT(zhan2361): 20/20 *)