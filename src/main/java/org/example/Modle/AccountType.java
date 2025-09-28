    package org.example.Modle;

    public enum AccountType {
        Courant,
        Epargne,
        Credit;

        public static AccountType fromString(String value) {
            for (AccountType type : AccountType.values()) {
                if (type.name().equalsIgnoreCase(value)) {
                    return type;
                }
            }
            return null;
        }
    }
