-- ============================================================
-- 1. Users
-- ============================================================
INSERT INTO "user" (id, name, phone_number, email, password) VALUES
(1, 'Ahmed Auditor', '0611111111', 'ahmed.auditor@example.com', 'hashed_pw1'),
(2, 'Sara Teller', '0622222222', 'sara.teller@example.com', 'hashed_pw2'),
(3, 'Omar Manager', '0633333333', 'omar.manager@example.com', 'hashed_pw3'),
(4, 'Hiba Client', '0644444444', 'hiba.client@example.com', 'hashed_pw4'),
(5, 'Youssef Client', '0655555555', 'youssef.client@example.com', 'hashed_pw5'),
(6, 'Laila Client', '0666666666', 'laila.client@example.com', 'hashed_pw6'),
(7, 'Ali Client', '0677777777', 'ali.client@example.com', 'hashed_pw7'),
(8, 'Nadia Client', '0688888888', 'nadia.client@example.com', 'hashed_pw8'),
(9, 'Khalid Client', '0699999999', 'khalid.client@example.com', 'hashed_pw9'),
(10, 'Mona Client', '0601010101', 'mona.client@example.com', 'hashed_pw10');

-- ============================================================
-- 2. Roles (Auditor, Teller, Manager)
-- ============================================================
INSERT INTO auditor (id) VALUES (1);
INSERT INTO teller (id) VALUES (2);
INSERT INTO manager (id) VALUES (3);

-- ============================================================
-- 3. Clients
-- ============================================================
INSERT INTO client (id, address, email) VALUES
(4, 'Rabat, Avenue Hassan II', 'hiba.client2@example.com'),
(5, 'Casablanca, Maarif', 'youssef.client2@example.com'),
(6, 'Fes, Medina', 'laila.client2@example.com'),
(7, 'Tangier, Corniche', 'ali.client2@example.com'),
(8, 'Marrakech, Gueliz', 'nadia.client2@example.com'),
(9, 'Agadir, Hay Salam', 'khalid.client2@example.com'),
(10, 'Oujda, Centre Ville', 'mona.client2@example.com');

-- ============================================================
-- 4. Accounts
-- ============================================================
INSERT INTO account (account_number, balance, type, currency, client) VALUES
('ACC1001', 5000.00, 'SAVINGS', 'MAD', 4),
('ACC1002', 12000.50, 'CURRENT', 'MAD', 5),
('ACC1003', 800.75, 'CREDIT', 'MAD', 6),
('ACC1004', 15000.00, 'SAVINGS', 'EUR', 7),
('ACC1005', 2300.00, 'CURRENT', 'USD', 8);

-- ============================================================
-- 5. Transactions
-- ============================================================
INSERT INTO transaction (amount, type, from_account, to_account) VALUES
(1000.00, 'DEPOSIT', NULL, 1),
(500.00, 'WITHDRAWAL', 2, NULL),
(300.00, 'TRANSFER', 2, 1),
(750.00, 'PAYMENT', 3, NULL);

-- ============================================================
-- 6. Credits
-- ============================================================
INSERT INTO credit (amount, interest_rate, duration_months, status, linked_account) VALUES
(10000.00, 5.5, 24, 'APPROVED', 2),
(2000.00, 7.0, 12, 'PENDING', 3);

-- ============================================================
-- 7. Fee Rules
-- ============================================================
INSERT INTO fee_rule (operation_type, mode, value, currency) VALUES
('TRANSFER', 'FIXED', 10.00, 'MAD'),
('WITHDRAWAL', 'PERCENTAGE', 2.50, 'MAD');

-- ============================================================
-- 8. Historique
-- ============================================================
INSERT INTO historique (street, city, auditor) VALUES
('Rue Mohammed V', 'Rabat', 1);
