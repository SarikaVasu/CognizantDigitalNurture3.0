CREATE TABLE Customers ( 
    CustomerID NUMBER PRIMARY KEY, 
    Name VARCHAR2(100), 
    DOB DATE, 
    Balance NUMBER, 
    LastModified DATE 
);

CREATE TABLE Accounts ( 
    AccountID NUMBER PRIMARY KEY, 
    CustomerID NUMBER, 
    AccountType VARCHAR2(20), 
    Balance NUMBER, 
    LastModified DATE, 
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) 
);

CREATE TABLE Transactions ( 
    TransactionID NUMBER PRIMARY KEY, 
    AccountID NUMBER, 
    TransactionDate DATE, 
    Amount NUMBER, 
    TransactionType VARCHAR2(10), 
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID) 
);

CREATE TABLE Loans ( 
    LoanID NUMBER PRIMARY KEY, 
    CustomerID NUMBER, 
    LoanAmount NUMBER, 
    InterestRate NUMBER, 
    StartDate DATE, 
    EndDate DATE, 
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) 
);

CREATE TABLE Employees ( 
    EmployeeID NUMBER PRIMARY KEY, 
    Name VARCHAR2(100), 
    Position VARCHAR2(50), 
    Salary NUMBER, 
    Department VARCHAR2(50), 
    HireDate DATE 
);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) 
VALUES (1, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) 
VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) 
VALUES (1, 1, SYSDATE, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType) 
VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate) 
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) 
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) 
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

DECLARE 
    v_customer_id Customers.CustomerID%TYPE; 
    v_dob Customers.DOB%TYPE; 
    v_current_date DATE := SYSDATE; 
    v_age NUMBER; 
BEGIN 
    -- Cursor to fetch all customers 
    FOR rec IN (SELECT CustomerID, DOB FROM Customers) LOOP 
        v_customer_id := rec.CustomerID; 
        v_dob := rec.DOB; 
         
        -- Calculate age 
        v_age := FLOOR(MONTHS_BETWEEN(v_current_date, v_dob) / 12); 
         
        IF v_age > 60 THEN 
            -- Update interest rates for loans of this customer 
            UPDATE Loans 
            SET InterestRate = InterestRate - 1 
            WHERE CustomerID = v_customer_id 
              AND InterestRate > 0; -- Ensure the rate doesn't go negative 
        END IF; 
    END LOOP; 
     
    COMMIT; 
END; 
/

ALTER TABLE Customers ADD (IsVIP CHAR(1) DEFAULT 'N');

DECLARE 
    v_customer_id Customers.CustomerID%TYPE; 
    v_balance Customers.Balance%TYPE; 
BEGIN 
    -- Cursor to fetch all customers with their balance 
    FOR rec IN (SELECT CustomerID, Balance FROM Customers) LOOP 
        v_customer_id := rec.CustomerID; 
        v_balance := rec.Balance; 
         
        IF v_balance > 10000 THEN 
            -- Update IsVIP flag for this customer 
            UPDATE Customers 
            SET IsVIP = 'Y' 
            WHERE CustomerID = v_customer_id; 
        END IF; 
    END LOOP; 
     
    COMMIT; 
END; 
/

DECLARE 
    v_customer_id Loans.CustomerID%TYPE; 
    v_loan_id Loans.LoanID%TYPE; 
    v_end_date Loans.EndDate%TYPE; 
    v_name Customers.Name%TYPE; 
BEGIN 
    -- Cursor to fetch loans due in the next 30 days 
    FOR rec IN (SELECT l.LoanID, l.CustomerID, l.EndDate, c.Name 
                FROM Loans l 
                JOIN Customers c ON l.CustomerID = c.CustomerID 
                WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30) LOOP 
         
        v_customer_id := rec.CustomerID; 
        v_loan_id := rec.LoanID; 
        v_end_date := rec.EndDate; 
        v_name := rec.Name; 
         
        -- Print reminder message 
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || v_name || ', your loan (ID: ' || v_loan_id || ') is due on ' || TO_CHAR(v_end_date, 'YYYY-MM-DD') || '. Please make sure to arrange payment.'); 
    END LOOP; 
END; 
/

create or replace procedure SafeTransferFunds ( 
    t_from_account IN NUMBER, 
    t_to_account IN NUMBER, 
	t_amount IN NUMBER 
) AS  
    v_from_balance Accounts.Balance%TYPE; 
	v_to_balance Accounts.Balance%TYPE; 
BEGIN 
    SAVEPOINT start_transaction; 
 
	SELECT Balance INTO v_from_balance 
        FROM Accounts 
        WHERE AccountId = t_from_account; 
 
	IF v_from_balance < t_amount THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account' || t_from_account); 
	END IF; 
 
	SELECT Balance INTO v_to_balance 
        FROM Accounts 
        WHERE AccountId = t_to_account; 
 
-- transaction 
    UPDATE Accounts 
    SET Balance = Balance - t_amount 
    WHERE AccountID = t_from_account; 
     
    UPDATE Accounts 
    SET Balance = Balance + t_amount 
    WHERE AccountID = t_to_account; 
     
    -- Commit the transaction 
    COMMIT; 
 
EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('Error: One or both accounts do not exist.'); 
        ROLLBACK TO start_transaction; 
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM); 
        ROLLBACK TO start_transaction; 
 
end SafeTransferFunds; 
/

CREATE OR REPLACE PROCEDURE UpdateSalary( 
    p_employee_id IN NUMBER, 
    p_percentage IN NUMBER 
) AS 
    v_current_salary Employees.Salary%TYPE; 
BEGIN 
     
    SELECT Salary INTO v_current_salary 
    FROM Employees 
    WHERE EmployeeID = p_employee_id; 
     
    UPDATE Employees 
    SET Salary = Salary + (Salary * p_percentage / 100) 
    WHERE EmployeeID = p_employee_id; 
     
    COMMIT; 
     
EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('Error: Employee with ID ' || p_employee_id || ' does not exist.'); 
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM); 
        ROLLBACK; 
END UpdateSalary; 
/

CREATE OR REPLACE PROCEDURE AddNewCustomer( 
    p_customer_id IN NUMBER, 
    p_name IN VARCHAR2, 
    p_dob IN DATE, 
    p_balance IN NUMBER 
) AS 
BEGIN 
    
    BEGIN 
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified) 
        VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE); 
         
        COMMIT; 
         
    EXCEPTION 
        WHEN DUP_VAL_ON_INDEX THEN 
            DBMS_OUTPUT.PUT_LINE('Error: Customer with ID ' || p_customer_id || ' already exists.'); 
            ROLLBACK; 
        WHEN OTHERS THEN 
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM); 
            ROLLBACK; 
    END; 
END AddNewCustomer; 
/

CREATE OR REPLACE PROCEDURE InterestProcessing AS 
    v_interest_rate NUMBER := 0.01; -- 1% interest rate 
BEGIN 
    
    UPDATE Accounts 
    SET Balance = Balance * (1 + v_interest_rate) 
    WHERE AccountType = 'Savings'; 
 
    COMMIT; 
 
    DBMS_OUTPUT.PUT_LINE('Monthly interest has been applied to all savings accounts.'); 
EXCEPTION 
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM); 
        ROLLBACK; 
END InterestProcessing; 
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus( 
    p_department IN VARCHAR2, 
    p_bonus_percentage IN NUMBER 
) AS 
BEGIN 
    
    UPDATE Employees 
    SET Salary = Salary + (Salary * p_bonus_percentage / 100) 
    WHERE Department = p_department; 
 
    COMMIT; 
 
    DBMS_OUTPUT.PUT_LINE('Bonuses have been applied to employees in department ' || p_department || '.'); 
EXCEPTION 
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM); 
        ROLLBACK; 
END UpdateEmployeeBonus; 
/

CREATE OR REPLACE PROCEDURE TransactionProcedure( 
    p_from_account IN NUMBER, 
    p_to_account IN NUMBER, 
    p_amount IN NUMBER 
) AS 
    v_from_balance Accounts.Balance%TYPE; 
BEGIN 
     
    SELECT Balance INTO v_from_balance 
    FROM Accounts 
    WHERE AccountID = p_from_account; 
     
    IF v_from_balance < p_amount THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ' || p_from_account); 
    END IF; 
     
    UPDATE Accounts 
    SET Balance = Balance - p_amount 
    WHERE AccountID = p_from_account; 
     
    UPDATE Accounts 
    SET Balance = Balance + p_amount 
    WHERE AccountID = p_to_account; 
 
    COMMIT; 
     
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from account ' || p_from_account || ' to account ' || p_to_account || '.'); 
EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('Error: One or both accounts do not exist.'); 
        ROLLBACK; 
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM); 
        ROLLBACK; 
END TransactionProcedure; 
/

CREATE OR REPLACE PROCEDURE TransferFunds( 
    p_from_account IN NUMBER, 
    p_to_account IN NUMBER, 
    p_amount IN NUMBER 
) AS 
    v_from_balance Accounts.Balance%TYPE; 
BEGIN 
     
    SELECT Balance INTO v_from_balance 
    FROM Accounts 
    WHERE AccountID = p_from_account; 
     
    IF v_from_balance < p_amount THEN 
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ' || p_from_account); 
    END IF; 
     
    UPDATE Accounts 
    SET Balance = Balance - p_amount 
    WHERE AccountID = p_from_account; 
     
    UPDATE Accounts 
    SET Balance = Balance + p_amount 
    WHERE AccountID = p_to_account; 
 
    COMMIT; 
     
    DBMS_OUTPUT.PUT_LINE('Transferred ' || p_amount || ' from account ' || p_from_account || ' to account ' || p_to_account || '.'); 
EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        DBMS_OUTPUT.PUT_LINE('Error: One or both accounts do not exist.'); 
        ROLLBACK; 
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM); 
        ROLLBACK; 
END TransferFunds; 
/

create or replace PROCEDURE ProcessMonthlyInterest AS  
    v_interest_rate NUMBER := 0.01; -- 1% interest rate  
BEGIN  
     
    UPDATE Accounts  
    SET Balance = Balance * (1 + v_interest_rate)  
    WHERE AccountType = 'Savings';  
  
    COMMIT;  
  
    DBMS_OUTPUT.PUT_LINE('Monthly interest has been applied to all savings accounts.');  
EXCEPTION  
    WHEN OTHERS THEN  
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);  
        ROLLBACK;  
END ProcessMonthlyInterest; 
/

CREATE OR REPLACE FUNCTION CalculateAge( 
    p_dob IN DATE 
) RETURN NUMBER AS 
    v_age NUMBER; 
BEGIN 
 
    v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12); 
 
    RETURN v_age; 
END CalculateAge; 
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment( 
    p_loan_amount IN NUMBER, 
    p_annual_interest_rate IN NUMBER, 
    p_loan_duration_years IN NUMBER 
) RETURN NUMBER AS 
    v_monthly_interest_rate NUMBER; 
    v_total_payments NUMBER; 
    v_monthly_installment NUMBER; 
BEGIN 
    -- Convert annual interest rate to monthly and percentage to decimal 
    v_monthly_interest_rate := (p_annual_interest_rate / 100) / 12; 
    v_total_payments := p_loan_duration_years * 12; 
 
    IF v_monthly_interest_rate > 0 THEN 
        v_monthly_installment := p_loan_amount * v_monthly_interest_rate /  
                                 (1 - POWER(1 + v_monthly_interest_rate, -v_total_payments)); 
    ELSE 
        v_monthly_installment := p_loan_amount / v_total_payments;  
    END IF; 
 
    RETURN v_monthly_installment; 
END CalculateMonthlyInstallment; 
/

CREATE OR REPLACE FUNCTION HasSufficientBalance( 
    p_account_id IN NUMBER, 
    p_amount IN NUMBER 
) RETURN BOOLEAN AS 
    v_balance Accounts.Balance%TYPE; 
BEGIN 
    
    SELECT Balance INTO v_balance 
    FROM Accounts 
    WHERE AccountID = p_account_id; 
 
    IF v_balance >= p_amount THEN 
        RETURN TRUE; 
    ELSE 
        RETURN FALSE; 
    END IF; 
     
EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        RETURN FALSE; 
    WHEN OTHERS THEN 
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM); 
        RETURN FALSE; 
END HasSufficientBalance; 
/

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified 
BEFORE UPDATE ON Customers 
FOR EACH ROW 
BEGIN 
    :NEW.LastModified := SYSDATE; 
END; 
/

CREATE OR REPLACE TRIGGER CheckTransactionRules 
BEFORE INSERT ON Transactions 
FOR EACH ROW 
DECLARE 
    v_balance NUMBER; 
BEGIN 
    -- Check if the transaction is a withdrawal 
    IF :NEW.TransactionType = 'WITHDRAWAL' THEN 
        -- Retrieve the current balance for the account 
        SELECT Balance INTO v_balance 
        FROM Accounts 
        WHERE AccountID = :NEW.AccountID; 
         
        -- Ensure withdrawal does not exceed the balance 
        IF :NEW.Amount > v_balance THEN 
            RAISE_APPLICATION_ERROR(-20001, 'Withdrawal amount exceeds account balance.'); 
        END IF; 
    ELSIF :NEW.TransactionType = 'DEPOSIT' THEN 
        -- Ensure deposit amount is positive 
        IF :NEW.Amount <= 0 THEN 
            RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.'); 
        END IF; 
    ELSE 
        RAISE_APPLICATION_ERROR(-20003, 'Invalid transaction type.'); 
    END IF; 
END; 
/

CREATE TABLE AuditLog ( 
    AuditID NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
    TransactionID NUMBER, 
    AccountID NUMBER, 
    TransactionDate DATE, 
    Amount NUMBER, 
    TransactionType VARCHAR2(10), 
    ActionDate DATE, 
    ActionType VARCHAR2(10) 
);

CREATE OR REPLACE TRIGGER LogTransaction 
AFTER INSERT ON Transactions 
FOR EACH ROW 
BEGIN 
    INSERT INTO AuditLog (TransactionID, AccountID, TransactionDate, Amount, TransactionType, ActionDate, ActionType) 
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.TransactionDate, :NEW.Amount, :NEW.TransactionType, SYSDATE, 'INSERT'); 
END; 
/

DECLARE 
    CURSOR c_transactions IS 
        SELECT c.CustomerID, c.Name, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType 
        FROM Customers c 
        JOIN Accounts a ON c.CustomerID = a.CustomerID 
        JOIN Transactions t ON a.AccountID = t.AccountID 
        WHERE t.TransactionDate >= TRUNC(SYSDATE, 'MONTH') AND t.TransactionDate < TRUNC(SYSDATE, 'MONTH') + INTERVAL '1' MONTH; 
 
    v_customerID Customers.CustomerID%TYPE; 
    v_name Customers.Name%TYPE; 
    v_transactionID Transactions.TransactionID%TYPE; 
    v_transactionDate Transactions.TransactionDate%TYPE; 
    v_amount Transactions.Amount%TYPE; 
    v_transactionType Transactions.TransactionType%TYPE; 
BEGIN 
    FOR r IN c_transactions LOOP 
        v_customerID := r.CustomerID; 
        v_name := r.Name; 
        v_transactionID := r.TransactionID; 
        v_transactionDate := r.TransactionDate; 
        v_amount := r.Amount; 
        v_transactionType := r.TransactionType; 
 
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customerID); 
        DBMS_OUTPUT.PUT_LINE('Customer Name: ' || v_name); 
        DBMS_OUTPUT.PUT_LINE('Transaction ID: ' || v_transactionID); 
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_transactionDate); 
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount); 
        DBMS_OUTPUT.PUT_LINE('Transaction Type: ' || v_transactionType); 
    END LOOP; 
END; 
/

DECLARE 
    CURSOR c_accounts IS 
        SELECT AccountID, Balance 
        FROM Accounts; 
 
    v_accountID Accounts.AccountID%TYPE; 
    v_balance Accounts.Balance%TYPE; 
    annual_fee NUMBER := 100;  
BEGIN 
    FOR r IN c_accounts LOOP 
        v_accountID := r.AccountID; 
        v_balance := r.Balance; 
 
        UPDATE Accounts 
        SET Balance = v_balance - annual_fee 
        WHERE AccountID = v_accountID; 
 
        DBMS_OUTPUT.PUT_LINE('Account ID: ' || v_accountID || ' - Annual fee applied. New balance: ' || (v_balance - annual_fee)); 
    END LOOP; 
END; 
/

DECLARE 
    CURSOR c_loans IS 
        SELECT LoanID, InterestRate 
        FROM Loans; 
 
    v_loanID Loans.LoanID%TYPE; 
    v_interestRate Loans.InterestRate%TYPE; 
    new_interest_rate NUMBER;  
BEGIN 
    FOR r IN c_loans LOOP 
        v_loanID := r.LoanID; 
        v_interestRate := r.InterestRate; 
 
        IF v_interestRate < 5 THEN 
            new_interest_rate := v_interestRate + 1; --Example 
        ELSE 
            new_interest_rate := v_interestRate; 
        END IF; 
 
        -- Update the loan's interest rate 
        UPDATE Loans 
        SET InterestRate = new_interest_rate 
        WHERE LoanID = v_loanID; 
 
        DBMS_OUTPUT.PUT_LINE('Loan ID: ' || v_loanID || ' - Interest rate updated to: ' || new_interest_rate); 
    END LOOP; 
END; 
/

CREATE OR REPLACE PACKAGE EmployeeManagement AS 
    PROCEDURE HireEmployee( 
        p_EmployeeID IN NUMBER, 
        p_Name IN VARCHAR2, 
        p_Position IN VARCHAR2, 
        p_Salary IN NUMBER, 
        p_Department IN VARCHAR2, 
        p_HireDate IN DATE 
    ); 
     
    PROCEDURE UpdateEmployeeDetails( 
        p_EmployeeID IN NUMBER, 
        p_Name IN VARCHAR2, 
        p_Position IN VARCHAR2, 
        p_Salary IN NUMBER, 
        p_Department IN VARCHAR2 
    ); 
     
    FUNCTION CalculateAnnualSalary( 
        p_EmployeeID IN NUMBER 
    ) RETURN NUMBER; 
END EmployeeManagement; 
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS 
 
    PROCEDURE HireEmployee( 
        p_EmployeeID IN NUMBER, 
        p_Name IN VARCHAR2, 
        p_Position IN VARCHAR2, 
        p_Salary IN NUMBER, 
        p_Department IN VARCHAR2, 
        p_HireDate IN DATE 
    ) IS 
    BEGIN 
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) 
        VALUES (p_EmployeeID, p_Name, p_Position, p_Salary, p_Department, p_HireDate); 
    END HireEmployee; 
 
    PROCEDURE UpdateEmployeeDetails( 
        p_EmployeeID IN NUMBER, 
        p_Name IN VARCHAR2, 
        p_Position IN VARCHAR2, 
        p_Salary IN NUMBER, 
        p_Department IN VARCHAR2 
    ) IS 
    BEGIN 
        UPDATE Employees 
        SET Name = p_Name, Position = p_Position, Salary = p_Salary, Department = p_Department 
        WHERE EmployeeID = p_EmployeeID; 
    END UpdateEmployeeDetails; 
 
    FUNCTION CalculateAnnualSalary( 
        p_EmployeeID IN NUMBER 
    ) RETURN NUMBER IS 
        v_Salary Employees.Salary%TYPE; 
        v_AnnualSalary NUMBER; 
    BEGIN 
        SELECT Salary INTO v_Salary 
        FROM Employees 
        WHERE EmployeeID = p_EmployeeID; 
        v_AnnualSalary := v_Salary * 12; -- example salary 
        RETURN v_AnnualSalary; 
    EXCEPTION 
        WHEN NO_DATA_FOUND THEN 
            RETURN NULL; 
    END CalculateAnnualSalary; 
 
END EmployeeManagement; 
/

CREATE OR REPLACE PACKAGE EmployeeManagement AS 
    PROCEDURE HireEmployee( 
        p_EmployeeID IN NUMBER, 
        p_Name IN VARCHAR2, 
        p_Position IN VARCHAR2, 
        p_Salary IN NUMBER, 
        p_Department IN VARCHAR2, 
        p_HireDate IN DATE 
    ); 
     
    PROCEDURE UpdateEmployeeDetails( 
        p_EmployeeID IN NUMBER, 
        p_Name IN VARCHAR2, 
        p_Position IN VARCHAR2, 
        p_Salary IN NUMBER, 
        p_Department IN VARCHAR2 
    ); 
     
    FUNCTION CalculateAnnualSalary( 
        p_EmployeeID IN NUMBER 
    ) RETURN NUMBER; 
END EmployeeManagement; 
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS 
 
    PROCEDURE HireEmployee( 
        p_EmployeeID IN NUMBER, 
        p_Name IN VARCHAR2, 
        p_Position IN VARCHAR2, 
        p_Salary IN NUMBER, 
        p_Department IN VARCHAR2, 
        p_HireDate IN DATE 
    ) IS 
    BEGIN 
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate) 
        VALUES (p_EmployeeID, p_Name, p_Position, p_Salary, p_Department, p_HireDate); 
    END HireEmployee; 
 
    PROCEDURE UpdateEmployeeDetails( 
        p_EmployeeID IN NUMBER, 
        p_Name IN VARCHAR2, 
        p_Position IN VARCHAR2, 
        p_Salary IN NUMBER, 
        p_Department IN VARCHAR2 
    ) IS 
    BEGIN 
        UPDATE Employees 
        SET Name = p_Name, Position = p_Position, Salary = p_Salary, Department = p_Department 
        WHERE EmployeeID = p_EmployeeID; 
    END UpdateEmployeeDetails; 
 
    FUNCTION CalculateAnnualSalary( 
        p_EmployeeID IN NUMBER 
    ) RETURN NUMBER IS 
        v_Salary Employees.Salary%TYPE; 
        v_AnnualSalary NUMBER; 
    BEGIN 
        SELECT Salary INTO v_Salary 
        FROM Employees 
        WHERE EmployeeID = p_EmployeeID; 
        v_AnnualSalary := v_Salary * 12; -- Assuming monthly salary 
        RETURN v_AnnualSalary; 
    EXCEPTION 
        WHEN NO_DATA_FOUND THEN 
            RETURN NULL; 
    END CalculateAnnualSalary; 
 
END EmployeeManagement; 
/

CREATE OR REPLACE PACKAGE AccountOperations AS 
    PROCEDURE OpenNewAccount( 
        p_AccountID IN NUMBER, 
        p_CustomerID IN NUMBER, 
        p_AccountType IN VARCHAR2, 
        p_Balance IN NUMBER 
    ); 
     
    PROCEDURE CloseAccount( 
        p_AccountID IN NUMBER 
    ); 
     
    FUNCTION GetTotalBalance( 
        p_CustomerID IN NUMBER 
    ) RETURN NUMBER; 
END AccountOperations; 
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS 
 
    PROCEDURE OpenNewAccount( 
        p_AccountID IN NUMBER, 
        p_CustomerID IN NUMBER, 
        p_AccountType IN VARCHAR2, 
        p_Balance IN NUMBER 
    ) IS 
    BEGIN 
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified) 
        VALUES (p_AccountID, p_CustomerID, p_AccountType, p_Balance, SYSDATE); 
    END OpenNewAccount; 
 
    PROCEDURE CloseAccount( 
        p_AccountID IN NUMBER 
    ) IS 
    BEGIN 
        DELETE FROM Accounts 
        WHERE AccountID = p_AccountID; 
    END CloseAccount; 
 
    FUNCTION GetTotalBalance( 
        p_CustomerID IN NUMBER 
    ) RETURN NUMBER IS 
        v_TotalBalance NUMBER; 
    BEGIN 
        SELECT SUM(Balance) INTO v_TotalBalance 
        FROM Accounts 
        WHERE CustomerID = p_CustomerID; 
        RETURN v_TotalBalance; 
    EXCEPTION 
        WHEN NO_DATA_FOUND THEN 
            RETURN 0; 
    END GetTotalBalance; 
 
END AccountOperations; 
/

