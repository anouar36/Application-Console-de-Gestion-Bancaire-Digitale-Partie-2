-- ============================================================
-- قاعدة بيانات PostgreSQL نظيفة بدون فهارس
-- ============================================================

-- أنواع ثابتة ENUM
CREATE TYPE account_type AS ENUM ('SAVINGS', 'CURRENT', 'CREDIT');
CREATE TYPE transaction_type AS ENUM ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER', 'PAYMENT');
CREATE TYPE credit_status AS ENUM ('PENDING', 'APPROVED', 'REJECTED', 'CLOSED');

-- ============================================================
-- جدول المستخدم
-- ============================================================
CREATE TABLE "user" (
    id INT PRIMARY KEY,                      -- يتم إدخاله يدوياً أو توليده من الخارج
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- ============================================================
-- الجداول الموروثة من user
-- ============================================================
CREATE TABLE auditor (
    id INT PRIMARY KEY REFERENCES "user"(id) ON DELETE CASCADE
);

CREATE TABLE teller (
    id INT PRIMARY KEY REFERENCES "user"(id) ON DELETE CASCADE
);

CREATE TABLE manager (
    id INT PRIMARY KEY REFERENCES "user"(id) ON DELETE CASCADE
);

-- ============================================================
-- جدول العميل
-- ============================================================
CREATE TABLE client (
    id INT PRIMARY KEY REFERENCES "user"(id) ON DELETE CASCADE,
    address VARCHAR(255),
    email VARCHAR(100) UNIQUE
);

-- ============================================================
-- جدول الحساب
-- ============================================================
CREATE TABLE account (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(50) UNIQUE NOT NULL,
    balance NUMERIC(15,2) NOT NULL DEFAULT 0.00,
    type account_type NOT NULL,
    currency VARCHAR(10) NOT NULL,
    client INT REFERENCES client(id) ON DELETE CASCADE
);

-- ============================================================
-- جدول المعاملات
-- ============================================================
CREATE TABLE transaction (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount NUMERIC(15,2) NOT NULL,
    type transaction_type NOT NULL,
    from_account INT REFERENCES account(id) ON DELETE SET NULL,
    to_account INT REFERENCES account(id) ON DELETE SET NULL
);

-- ============================================================
-- جدول القروض
-- ============================================================
CREATE TABLE credit (
    id SERIAL PRIMARY KEY,
    amount NUMERIC(15,2) NOT NULL,
    interest_rate DOUBLE PRECISION NOT NULL,
    duration_months INT NOT NULL,
    status credit_status NOT NULL DEFAULT 'PENDING',
    linked_account INT REFERENCES account(id) ON DELETE CASCADE
);

-- ============================================================
-- جدول الرسوم (FeeRule)
-- ============================================================
CREATE TABLE fee_rule (
    id SERIAL PRIMARY KEY,
    operation_type VARCHAR(50) NOT NULL,
    mode VARCHAR(50) NOT NULL,
    value NUMERIC(10,2) NOT NULL,
    currency VARCHAR(10) NOT NULL
);

-- ============================================================
-- جدول السجل (Historique)
-- ============================================================
CREATE TABLE historique (
    id SERIAL PRIMARY KEY,
    street VARCHAR(100),
    city VARCHAR(100),
    auditor INT REFERENCES auditor(id) ON DELETE CASCADE
);

-- ============================================================
-- جدول التقارير (Rapport)
-- ============================================================
CREATE TABLE rapport (
    id SERIAL PRIMARY KEY,
    auditor INT REFERENCES auditor(id) ON DELETE CASCADE
);

-- ============================================================
-- جدول الإحصائيات (Statistique)
-- ============================================================
CREATE TABLE statistique (
    id SERIAL PRIMARY KEY,
    rapport INT REFERENCES rapport(id) ON DELETE CASCADE
);

-- ============================================================
-- العلاقات المتعددة (Many-to-Many)
-- ============================================================

-- Rapport ↔ Credit
CREATE TABLE rapport_credit (
    rapport INT REFERENCES rapport(id) ON DELETE CASCADE,
    credit INT REFERENCES credit(id) ON DELETE CASCADE,
    PRIMARY KEY (rapport, credit)
);

-- Rapport ↔ Transaction
CREATE TABLE rapport_transaction (
    rapport INT REFERENCES rapport(id) ON DELETE CASCADE,
    transaction INT REFERENCES transaction(id) ON DELETE CASCADE,
    PRIMARY KEY (rapport, transaction)
);

-- Rapport ↔ Account
CREATE TABLE rapport_account (
    rapport INT REFERENCES rapport(id) ON DELETE CASCADE,
    account INT REFERENCES account(id) ON DELETE CASCADE,
    PRIMARY KEY (rapport, account)
);
