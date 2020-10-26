---
layout: page
title: User Guide
---
<div>
    <div>
        <img src="images/TBM.png" width="160px">
    </div>
    <p>
        <h3><i>Welcome to the Travelling BusinessMan User Guide!</i></h3>
    </p>
    <p>
        <strong><i>Travelling BusinessMan</i></strong>, <strong><i>TBM</i></strong>, is a companion that goes beyond being just a simple contact management application.
    </p>
    <p>
        <strong><i>TBM</i></strong> sorts and organises your clients for you, streamlines your workflow, improves business efficiency and so much more. All this is at the convenience of the Command-Line Interface. <strong><i>TBM</i></strong> does more than just tracking your clients!<br>
    </p>
    <p>
        This user guide will be a starting point for you in getting yourself oriented with how <strong><i>TBM</i></strong> works and how you can integrate it into your professional journey!
    </p>
</div>

## Table of contents

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quickstart

<p>Before you start using <strong><i>TBM</i></strong>, </p>

1. Ensure you have Java 11

1. Download the application file (.jar file) from [here](#https://github.com/AY2021S1-CS2103T-F11-4/tp/releases)

1. Move file to a directory of your choice and run `java -jar tbm.jar`

1. Type any command in the command box and press Enter to execute it.

1. (Recommended) The [`help`](#Viewing-help-help) command will show a quick page of some commonly used commands to get you started quickly, if you are a new user of TBM

   * Type the **`help`** command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

1. Alternatively, you can refer to the [Features](#features) below for details of each command.

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

### Viewing all saved clients: `list`

Shows a list of all persons in the address book.

Format: `list`

### Viewing help: `help`

Shows commonly used commands for TBM in a new help window, pressing Esc will close this window.

Format: `help`

### Adding a client: `client add`

Adds a new client to the app.

Format: `client add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COUNTRY_CODE tz/TIMEZONE [ce/CONTRACT_EXPIRY_DATE]`

Examples:

* `client add 5 n/Katya p/98123456 e/katya@yahoo.com a/Vladivostok, Nevelskogo, bld. 15, appt. 256 c/RUS tz/GMT+3 ce/2-3-2020`<br>
  This adds a new client with name **Katya**, phone number **98123456**, email **katya@yahoo.com**, address **Vladivostok, Nevelskogo, bld. 15, appt. 256**, country **Russia**, timezone **GMT+3**, contract expiry date **2 Mar 2020**.

### Viewing clients: `client view`

View the client specified by the index.

Format: `client view INDEX`

Examples:

* `client view 2` Views information for client at index 2 of the list panel

### Finding clients: `client find`

Finds clients whose names contain any of the given keywords, or whose country of residence contains any of the given keywords.

Format: `client find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name and country are searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Clients matching at least one keyword will be returned (i.e. OR search). e.g. `Hans Bo Russia` will return `Hans Gruber`, `Bo Yang`, `Alice Katya`

Examples:

* `client find katya` Finds all clients with names that matches **katya**.
* `client find Russia` Finds all clients associated to the country of **Russia**.
* `client find Katya Russia` Finds all clients who are either named **katya** or are associated to **Russia**.

### Editing a client: `client edit`

Edits a client's information by their index in the list view.

Format: `client edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COUNTRY_CODE] [tz/TIMEZONE] [ce/CONTRACT_EXPIRY]`

* COUNTRY follows the ISO3166 Format of two-letter country codes. [List of country codes](https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes)

Examples:

* `client edit 5 n/Katya` Only edits **name**, other fields remain the same
* `client edit 4 n/Alek p/34842097 e/dcsdcr@nus.edu.sg` Edits **name**, **phone number** and **email**, other fields remain the same.
* `client edit 3 c/JP tz/GMT+7` Edits **country** to Japan and **timezone** to GMT+7

### Deleting a client: `client delete`

Deletes a client by their index in the list view.

Format: `client delete INDEX`

Examples:

* `client delete 5` Deletes the client at index 5 of the list panel.

### Saving data

Automatically saved after every change.

### Adding client notes: `client note add`

Format: `client note add CLIENT_INDEX nt/NOTE_STRING [t/TAG]...`

Examples:

* `client note add 1 t/pref nt/wants meetings to be as short as possible (preferably 30 mins)`
* `client note add 2 t/pref nt/prefers emails to calls`
* `client note add 4 t/meeting nt/need to slowly convince him to sign the contract`

### Deleting client notes: `client note delete`

Deletes a note of a client (denoted by client's index) by the note's index.

Format: `client note delete CLIENT_INDEX NOTE_INDEX`

Examples:

Given a list of notes:

```
Client: 3
Notes:
1. Loves dogs [tag: pref]
2. Hates cats [tag: pref]
```

`client note delete 3 2`

The above command deletes the note regarding "Hates cats". The resulting information will look like
```
Client: 3
Notes:
1. Loves dogs [tag: pref]
```

### Editing client notes: `client note edit`

Edits a note of a client (denoted by client's index) by the note's index.

Format: `client note edit CLIENT_INDEX NOTE_INDEX [nt/NOTE_STRING] [t/TAG]...`

Examples:

Given a list of notes:

```
Client: 3
Notes:
1. Loves dogs [tag: pref]
2. Hates cats [tag: pref]
3. Apprehensive of resigning contract [tag: meeting]
```

* `client note edit 3 2 nt/Loves cats`

The original note containing "Hates cats" will be changed to "Loves cats" while retaining its original tag. The resulting list will look like:

```
Client: 3
Notes:
1. Loves dogs [tag: pref]
2. Loves cats [tag: pref]
3. Apprehensive of resigning contract [tag: meeting]
```

### Filtering clients by country: `country filter`

Filters the list of clients by a specified country.

Format: `country filter c/COUNTRY_CODE`

* COUNTRY_CODE is a 2-letter country code that follows the ISO3166 specification. [List of country codes](https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes)

Examples:

* `country filter c/SG` Filters by contacts in Singapore
* `country filter c/RU` Filters by contacts in Russia

### Viewing notes for a country: `country note view`

Views the list of country notes from the Country which corresponds to the given country code.
If no country code is given, all country notes in TBM will be shown.

Format: `country note view [c/COUNTRY_CODE]`

* COUNTRY_CODE is a 2-letter country code that follows the ISO3166 specification. [List of country codes](https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes)

Examples:

* `country note view` 
* `country note view c/SG` 
* `country note view c/IN` 

### Adding notes for a country: `country note add`

Adds a note that is associated with a specific country.

Format: `country note add c/COUNTRY_CODE nt/NOTE_STRING`

* COUNTRY_CODE is a 2-letter country code that follows the ISO3166 specification. [List of country codes](https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes)

Examples:

* `country note add c/SG nt/has one of the lowest coporate taxes in the world`
* `country note add c/CN nt/building good relations (guanxi) is important when conducting business here`
* `country note add c/IN nt/is world's fastest growing economy`

### Deleting notes for a country: `country note delete`

Deletes a note that is associated with a specific country.

Format: `country note delete INDEX`

* COUNTRY_CODE is a 2-letter country code that follows the ISO3166 specification. [List of country codes](https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes)

Examples:

* `country note delete 1` Deletes the country note at index 1 of the country notes list panel.

### Getting suggestions on clients: `suggest`

Obtains a list of clients based on the suggestion type(s) passed in.

Format: `suggest by/SUGGESTION_TYPE [by/SUGGESTION_TYPE]...`

* SUGGESTION_TYPE must be one of the following: `frequency`, `available` or `contract`

Examples:

* `suggest by/available` Obtains a list of clients where the time is 1800-2200 in the client's timezone (off work hours).
* `suggest by/frequency` Obtains a list of clients based on the last time their details were edited in TBM. Clients who have not been contacted for a longer period will be the first in the list.
* `suggest by/contract` Obtains a list of clients based on their current contract details. Clients whose contracts are expiring will be shown first.
* `suggest by/contract by/available` Similar to `suggest by/contract` but only available clients will be shown.


### Clearing all entries: `clear`

Deletes all existing information from the application. You will start from a clean slate.

### Exiting the program: `exit`

Exits the program.

Format: `exit`

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TBM home folder.<br>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**List all clients** | `list`
**Add client** | `client add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/COUNTRY_CODE tz/TIMEZONE [ce/CONTRACT_EXPIRY_DATE]` <br> e.g., `client add 5 n/Katya p/98123456 e/katya@yahoo.com a/Vladivostok, Nevelskogo, bld. 15, appt. 256 c/RUS tz/GMT+3 ce/22-12-2020`
**Edit client** | `client edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/COUNTRY_CODE] [tz/TIMEZONE] [ce/CONTRACT_EXPIRY_DATE]`<br> e.g.,`client edit 3 c/JP tz/GMT+7`
**View client** | `client view INDEX` <br> e.g., `client view 2`
**Find client** | `client find KEYWORD [MORE_KEYWORDS]`<br> e.g., `client find Hans`
**Delete client** | `client delete INDEX`<br> e.g., `client delete 3`
**Add client note** | `client note add CLIENT_INDEX nt/NOTE_STRING [t/TAG]...` <br> e.g., `client note add 4 t/meeting nt/need to slowly convince him to sign the contract`
**Delete client note** | `client note delete CLIENT_INDEX NOTE_INDEX` <br> e.g., `client note delete 3 2`
**Edit client note** | `client note edit CLIENT_INDEX NOTE_INDEX [nt/NOTE_STRING] [t/TAG]...` <br> e.g., `client note edit 3 2 nt/Loves cats`
**Filter by country** | `country filter c/COUNTRY_CODE` <br> e.g., `country filter c/SG`
**View country note** | `country note view [c/COUNTRY_CODE]` <br> e.g., `country note view c/SG`
**Add country note** | `country note add c/COUNTRY_CODE nt/NOTE_STRING` <br> e.g., `country note add c/SG nt/has one of the lowest coporate taxes in the world`
**Delete country note** | `country note delete INDEX` <br> e.g., `country note delete 1`
**Get suggestions** | `suggest by/SUGGESTION_TYPE [by/SUGGESTION_TYPE]...` <br> e.g., `suggest by/available by/frequency`
**Clear** | `clear`
**Exit** | `exit`
**Help** | `help`
