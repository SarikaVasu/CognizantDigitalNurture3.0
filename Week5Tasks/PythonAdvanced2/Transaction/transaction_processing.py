import logging
from datetime import datetime

logging.basicConfig(filename='transaction_errors.log', level=logging.ERROR, format='%(asctime)s - %(levelname)s - %(message)s')

def log_error(message):
    logging.error(message)

def validate_transaction(transaction):
    if not isinstance(transaction, dict):
        return False, "Transaction data must be a dictionary."
    if 'amount' not in transaction or not isinstance(transaction['amount'], (int, float)):
        return False, "Transaction must contain an 'amount' that is a number."
    if 'description' not in transaction or not isinstance(transaction['description'], str):
        return False, "Transaction must contain a 'description' that is a string."
    if transaction['amount'] <= 0:
        return False, "Transaction amount must be greater than zero."
    return True, ""

def process_transaction(transaction):
    try:
        valid, message = validate_transaction(transaction)
        if not valid:
            raise ValueError(message)

        print(f"Processing transaction: {transaction['description']} for amount ${transaction['amount']:.2f}")
        print("Transaction processed successfully.")
    
    except ValueError as ve:
        error_message = f"ValueError: {ve}"
        print(f"Error: {ve}")
        log_error(error_message)
    
    except TypeError as te:
        error_message = f"TypeError: {te}"
        print(f"Error: Invalid type encountered.")
        log_error(error_message)
    
    except Exception as e:
        error_message = f"Unexpected error: {e}"
        print(f"An unexpected error occurred: {e}")
        log_error(error_message)
    
    finally:
        print("Transaction processing complete.")

def main():
    while True:
        try:
            transaction_input = input("Enter transaction data (format: amount,description) or 'quit' to exit: ")
            if transaction_input.lower() == 'quit':
                break
            
            parts = transaction_input.split(',')
            if len(parts) != 2:
                print("Invalid input format. Please enter in the format: amount,description")
                continue
            
            amount = float(parts[0])
            description = parts[1].strip()
            
            transaction = {
                'amount': amount,
                'description': description
            }
            
            process_transaction(transaction)
        
        except ValueError as ve:
            print(f"Error: {ve}")
            log_error(f"ValueError: {ve}")
        
        except Exception as e:
            print(f"An unexpected error occurred: {e}")
            log_error(f"Unexpected error: {e}")

if __name__ == "__main__":
    main()
