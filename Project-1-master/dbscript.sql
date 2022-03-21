CREATE DATABASE ers;

\c ers;

CREATE TABLE employee_details(employee_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1), 
                            employee_first_name VARCHAR(20),
                            employee_last_name VARCHAR(20), 
                            employee_phone_number VARCHAR(20), 
                            employee_address VARCHAR(200), 
                            employee_password VARCHAR(20),
                            employee_image_url VARCHAR(200),
                            PRIMARY KEY(employee_id));
                                
CREATE TABLE manager_details(manager_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1), 
                            manager_first_name VARCHAR(20),
                            manager_last_name VARCHAR(20), 
                            manager_phone_number VARCHAR(20), 
                            manager_address VARCHAR(200), 
                            manager_password VARCHAR(20),
                            manager_image_url VARCHAR(200),
                            PRIMARY KEY(manager_id));

CREATE TABLE reimbursement_details(reimbursement_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1), 
                            requesting_employee_id INT,
                            reimbursement_amount NUMERIC(10,2), 
                            reimbursement_pending BOOLEAN,
                            date_of_request DATE NOT NULL DEFAULT CURRENT_DATE,
                            PRIMARY KEY(reimbursement_id));

CREATE TABLE resolved_reimbursements(resolved_reimbursement_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1), 
                            reimbursement_id INT,
                            request_approved BOOLEAN, 
                            date_resolved DATE NOT NULL DEFAULT CURRENT_DATE,
                            PRIMARY KEY(reimbursement_id));

ALTER TABLE reimbursement_details ADD 
   CONSTRAINT fk_requesting_employee_id 
      FOREIGN KEY (requesting_employee_id)
      REFERENCES employee_details (employee_id)
      ON DELETE CASCADE;

ALTER TABLE resolved_reimbursements ADD 
   CONSTRAINT fk_reimbursement_id 
      FOREIGN KEY (reimbursement_id)
      REFERENCES reimbursement_details (reimbursement_id)
      ON DELETE CASCADE;

INSERT INTO employee_details(employee_first_name, employee_last_name, employee_phone_number, employee_password, employee_image_url) VALUES('Celia', 'Mae',  '(546)654-1654' , '665 Weelia Ct.' , '1234', 'https://images.unsplash.com/photo-1611432579699-484f7990b127?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8aGVhZHNob3R8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60');
INSERT INTO manager_details(manager_first_name, manager_last_name, manager_phone_number, manager_password, manager_image_url) VALUES('Roz', 'Slug',  '(546)354-3218' , '354 Watching Rd.' , '2468', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg/800px-Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg?20220222130835');
