import os
import pickle

TEXT_FILE = "contacts.txt"
# BINARY_FILE = "contacts.bin"
BINARY_FILE = "contacts.dat"

def contact_readFromTextFile():
    contacts = []
    try:
        with open(TEXT_FILE, 'r') as file:
            contacts = file.readlines()
            return [contact.strip() for contact in contacts]
    except FileNotFoundError:
        print("Text file not found. The contact list is empty")
        return []
    

def contact_writeToTextFile(contacts):
    with open(TEXT_FILE, 'w') as file:
        for contact in contacts:
            file.write(contact + '\n')

def contact_AddToTextFile(contact):
    contacts = contact_readFromTextFile()
    contacts.append(contact)
    contact_writeToTextFile(contacts)

def contact_removeFromTextFile(contact):
    contacts = contact_readFromTextFile()
    if contact in contacts:
        contacts.remove(contact)
        contact_writeToTextFile(contacts)
    else:
        print("No such contact found")

def contact_displayFromTextFile():
    contacts = contact_readFromTextFile()
    if contacts:
        print("Contacts: ")
        for contact in contacts:
            print(contact)
    else:
        print("No contacts")


#Binary file functions
def binary_writeToBinaryFile(contacts):
    with open (BINARY_FILE, 'wb') as file:
        pickle.dump(contacts, file)

def binary_loadFromBinaryFile():
    contacts = []
    try:
        with open(BINARY_FILE, 'rb') as file:
            contacts = pickle.load(file)
    except FileNotFoundError:
        print("File not found")
    except pickle.UnpicklingError:
        print("Error reading file")
    except Exception as e:
        print(e)
    return contacts


def binary_addToBinaryFile(contact):
    contacts = binary_loadFromBinaryFile()
    contacts.append(contact)
    binary_writeToBinaryFile(contacts)

def binary_removeFromBinaryFile(contact):
    contacts = binary_loadFromBinaryFile()
    if contact in contacts:
        contacts.remove(contact)
        binary_writeToBinaryFile(contacts)
    else:
        print('Contact not fount')

def binary_displayFromBinaryFile():
    contacts = binary_loadFromBinaryFile()
    if contacts:
        print("Contacts: ")
        for contact in contacts:
            print(contact)
    else:
        print("No contacts")
        

def main():
    while True:
        print("\nContact Management System")
        print("1. Display contacts")
        print("2. Add contact")
        print("3. Remove contact")
        print("4. Exit")

        choice = input("Enter your choice: ")

        if choice == '1':
            print("\nText File Contacts:")
            contact_displayFromTextFile()
            print("\nBinary File Contacts:")
            binary_displayFromBinaryFile()
        elif choice == '2':
            contact = input("Enter contact (Name - Phone): ")
            contact_AddToTextFile(contact)
            binary_addToBinaryFile(contact)
        elif choice == '3':
            contact = input("Enter contact to remove (Name - Phone): ")
            contact_removeFromTextFile(contact)
            binary_removeFromBinaryFile(contact)
        elif choice == '4':
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()

# if __name__ == "__main__":
#     binary_displayFromBinaryFile()
#     binary_addToBinaryFile('Alice Cooper - 555-4321')
#     binary_addToBinaryFile('Bob Brown - 555-8765')
#     binary_displayFromBinaryFile()
#     binary_removeFromBinaryFile('Alice Cooper - 555-4321')
#     binary_displayFromBinaryFile()
