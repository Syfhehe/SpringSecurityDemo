MERGE INTO user (user_name, password, active, role) 
   KEY(user_name)
VALUES
  ('aaa@ibm.com', 'b8ba8c5ba8541911873b459e3db2aee3', true, 'ADMIN'),
  ('bbb@ibm.com', 'fbfda36443bd9923de9f5b568156a242', true, 'USER'),
  ('ccc@ibm.com', '1e85641a45ac9c9304216a6edc109b41', true, 'DEVELOPER');
