INSERT INTO app_user (id, ref, first_name, last_name, email, phone) VALUES
                                                                        ('usr-1', 'REF-001', 'John', 'Doe', 'john.doe@example.com', '+1234567890'),
                                                                        ('usr-2', 'REF-002', 'Jane', 'Smith', 'jane.smith@example.com', '+0987654321'),
                                                                        ('usr-3', 'REF-003', 'Alice', 'Johnson', 'alice.johnson@example.com', '+1122334455');

INSERT INTO cash_flow (id, created_at, amount, user_id, flow_type, comment, reason, frequency) VALUES
                                                                                                   ('cf-1', '2026-08-01T10:00:00Z', 500.00, 'usr-1', 'DONATION', 'Monthly support donation', NULL, NULL),
                                                                                                   ('cf-2', '2026-08-05T14:30:00Z', 1500.00, 'usr-2', 'DONATION', 'Annual sponsor donation', NULL, NULL),
                                                                                                   ('cf-3', '2026-08-10T09:15:00Z', 200.00, 'usr-3', 'DONATION', 'Community help donation', NULL, NULL),
                                                                                                   ('cf-4', '2026-08-02T11:00:00Z', 120.00, 'usr-1', 'EXPENSE', NULL, 'Office Internet bill', 'MONTHLY'),
                                                                                                   ('cf-5', '2026-08-06T16:00:00Z', 450.00, 'usr-2', 'EXPENSE', NULL, 'Laptops maintenance', 'NONE'),
                                                                                                   ('cf-6', '2026-08-12T08:00:00Z', 80.00, 'usr-3', 'EXPENSE', NULL, 'Weekly cleaning supplies', 'WEEKLY');