# Install Apache Maven
## Steps 1: Install Oracle JDK
1. Visit <a href="https://www.oracle.com/java/technologies/javase-jdk16-downloads.html"> this link </a> to download the latest JDK.
> Note
>
> In this article, we will use the `jdk-16.0.2_windows-x64_bin.exe`
2. Open the `jdk-16.0.2_windows-x64_bin.exe` you just downloaded.
3. In the `Welcome to Installation`, hit `Next` buton.
4. Choose your Install Directory by press `Change` then hit `Next`
5. Wait for JDK to install
6. When it's done, press `Close`.
## Steps 2: Add JAVA_HOME
1. Type `advanced system settings` in the search Windows box (beside the Windows start button), clicks `View advanced system settings`.
2. Select `Advance` tab, clicks `Environment Variables`.
3. In System variables, clicks `New...` button to add a new `JAVA_HOME` variable and point it to the JDK installed folder.
> Note 
>
> Don’t include the \bin folder, just the JDK path. For example
>
> Correct – C:\Program Files\Java\jdk16.0.2
> 
> Wrong – C:\Program Files\Java\jdk16.0.2\bin
4. find `PATH`, clicks `edit...` button, clicks on `New` button, and add this `%JAVA_HOME%\bin`.
> Note
>
> Puts the `%JAVA_HOME%\bin` in `PATH` make all the Java’s commands (java, javac, jstack and etc) are accessible from everywhere.
## Steps 3: Download Apache Maven
1. Visit <a href="https://maven.apache.org/download.cgi"> this link </a> to download the latest Apache Maven.
2. Unzip it to folder. In this article, we are using `c:\opt\apache-maven-3.6.0`
> Note
>
> That’s all, just download and unzip, installation is NOT required.
## Step 4: Add MAVEN_HOME system variable
1. Press Windows key, type `adva` and clicks on the `View advanced system settings`
2. In System Properties dialog, select `Advanced` tab and clicks on the `Environment Variables...` button.
3. In "Environment variables" dialog, `System variables`, Clicks on the `New...` button and add a `MAVEN_HOME` variable and point it to `c:\opt\apache-maven-3.6.0`.
4. In system variables, find `PATH`, clicks on the `Edit...` button. In “Edit environment variable” dialog, clicks on the `New` button and add this `%MAVEN_HOME%\bin`.
## Finish