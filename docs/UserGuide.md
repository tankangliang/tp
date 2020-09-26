---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

## Table of contents

- [Table of contents](#table-of-contents)
- [Quickstart](#quickstart)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a client: `client add`](#adding-a-client-client-add)
  - [Viewing clients: `client view`](#viewing-clients-client-view)
  - [Finding clients: `client find`](#finding-clients-client-find)
  - [Updating a client: `client update`](#updating-a-client-client-update)
  - [Listing all persons : `list`](#listing-all-persons--list)
  - [Deleting a client: `client delete`](#deleting-a-client-client-delete)
  - [Saving data](#saving-data)
  - [Adding client notes: `client note add`](#adding-client-notes-client-note-add)
  - [Adding notes for a country: `country note`](#adding-notes-for-a-country-country-note)
  - [Clearing all entries: `clear`](#clearing-all-entries-clear)
  - [Exiting the program: `exit`](#exiting-the-program-exit)
- [FAQ](#faq)
- [Command summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quickstart

1. Ensure you have Java 11

2. Download the application file (.jar file) from [here](#https://github.com/AY2021S1-CS2103T-F11-4/tp/releases)

3. Move file to a directory of your choice and run `java -jar tbm.jar`

4. Type any command in the command box and press Enter to execute it.

5. (Recommended) The [`help`](#Viewing-help-help) command will show a quick page of some commonly used commands to get
you started quickly, if you are a new user of TBM

   * Type the **`help`** command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   open the help window.<br>

6. Alternatively, you can refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help: `help` 

Shows commonly used commands for TBM.

Format: `help`

### Adding a client: `client add` 

Adds a new client to the app.

Format: `client add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COUNTRY tz/TIMEZONE`

Examples: 

* `client add 5 n/Katya p/98123456 e/katya@yahoo.com a/Vladivostok, Nevelskogo, bld. 15, appt. 256 c/RUS tz/GMT+3`
<br> This adds a new client with name **Katya**, phone number **98123456**, email **katya@yahoo.com**, address **Vladivostok,
Nevelskogo, bld. 15, appt. 256**, country **Russia**, timezone **GMT+3**.

### Viewing clients: `client view`

View the client specified by the index. If no index is specified, all clients will be listed.

Format: `client view [INDEX]`

Examples:
* `client view 2` Views information for client at index 2
* `client view` Lists all clients

### Finding clients: `client find`

Finds clients whose names contain any of the given keywords.

Format: `client find KEYWORD [MORE_KEYWORDS]`
* The search is case-insensitive. e.g hans will match Hans
* The order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* Only the name is searched.
* Only full words will be matched e.g. Han will not match Hans
* Persons matching at least one keyword will be returned (i.e. OR search). e.g. Hans Bo will return Hans Gruber, Bo Yang

Examples: 

* `client find katya` Finds all clients with names that matches **katya**.


### Updating a client: `client update`

Updates a client's information by their index in the list view.

Format: `client update INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COUNTRY] [tz/TIMEZONE]`
* COUNTRY follows the ISO3166 Format, which can be in the form of a 2, 3 or 4 letter country code. [List of country codes](https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes)

Examples: 

* `client update 5 n/Katya` Only updates **name**, other fields remain the same
* `client update 4 n/Alek p/34842097 e/dcsdcr@nus.edu.sg` Updates **name**, **phone number** and **email**, other fields remain the same.


### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`




### Deleting a client: `client delete`

Deletes a client by their index in the list view.

Format: `client delete INDEX`

Examples: 

* `client delete 5`

### Saving data

Automatically saved after every change.

### Adding client notes: `client note add`

Format: `client note add CLIENT_INDEX t/TAG NOTE_STRING`

Examples: 

* `client note add 1 t/pref wants meetings to be as short as possible (preferably 30 mins)`
* `client note add 2 t/pref prefers emails to calls`
* `client note add 4 t/meeting need to slowly convince him to sign the contract`

### Adding notes for a country: `country note`

Format: `country note c/COUNTRY NOTE_STRING`

* COUNTRY follows the ISO3166 Format, which can be in the form of a 2, 3 or 4 letter country code. [List of country codes](https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes)

Examples:

* `country note c/SG people love to queue for things`

### Clearing all entries: `clear`

Deletes all information from the application, you will start from a clean slate.

### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TBM home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Add Country Note** | `country note c/COUNTRY NOTE_STRING` <br> e.g., `country note c/SG people love to queue for things`
**Clear** | `clear`
**Delete client** | `client delete INDEX`<br> e.g., `client delete 3`
**Add client note** | `client note add CLIENT_INDEX t/TAG NOTE_STRING` <br> e.g., `client note add 4 t/meeting need to slowly convince him to sign the contract`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Exit** | `exit`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**Help** | `help`
**List** | `list`
