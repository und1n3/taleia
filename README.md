# Welcome to Taleia App!

This app let's you create random sets of words in order to break the writer's block.
It has 3 main screens:

- **Scene**: It generates 3 words for *Who*, *What* and *Where*
- **Character**: It generates 4 words for  *Who*, *Adjective* and some kind of characteristics with a verb and a noun.
- **Challenge** : It generates 3 words for *what* , *writting style* and *limitation*

# Features

Implemented a Log In / Sign In functionality with AppWrite ( )

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/main_screen.png" alt="drawing" width="200"/>

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/info.png" alt="drawing" width="200"/>


Once logged in the user can save the combinations (stored in the AppWrite platform).

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/loged_menu.png" alt="drawing" width="200"/>

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/login_menu.png" alt="drawing" width="200"/>

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/name_alert.png" alt="drawing" width="200"/>


They will be displayed in a Toast message (scrollView still in progress)

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/saved_combo.png" alt="drawing" width="200"/>

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/toast.png" alt="drawing" width="200"/>


Here we can see the saved files in Appwrite:

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/app_users.png" alt="drawing" height="300"/>

<img src="https://github.com/und1n3/taleia/blob/main/readme_images/app_docs.png" alt="drawing" height="300"/>

# How to implement Appwrite:
In order for it to run, we need to create an Android project and inside this projects the following three collections:

### Scene
Will be used to store the scene combinations. It has to be set with the following Rules and Permissions:

 - **Rules**:
	 - who : *Text*
	 - what : *Text*
	 - where : *Text*

 - **Permissions**:
	 - read access : *
	 - write access : *

### Character
Will be to  store the character combinations. It has to be set with the following Rules and Permissions:

 - **Rules**:
	 - who : *Text*
	 - adj : *Text*
	 - tick1 : *Text*
	 - tick2 : *Text*

 - **Permissions**:
	 - read access: *
	 - write access: *

### Challange
Will be to store the challange combinations. It has to be set with the following Rules and Permissions:

 - **Rules**:
	 - what : *Text*
	 - style : *Text*
	 - restrict : *Text*

 - **Permissions**:
	 - read access : *
	 - write access : *


### Hope you like it!

## WIP

- [ ] Display the list of saved Instances in a scroll view
- [ ] Display the three options in a menu Tab in the saved prompts activity
- [ ] Add icons to the Drawer Navigation Menu

