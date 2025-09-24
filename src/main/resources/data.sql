-- Insert initial vehicle rates
INSERT INTO vehicle_rate (vehicle_type, hourly_rate) VALUES ('FOUR_WHEELER', 30.0) ON CONFLICT DO NOTHING;
INSERT INTO vehicle_rate (vehicle_type, hourly_rate) VALUES ('TWO_WHEELER', 20.0) ON CONFLICT DO NOTHING;

-- Insert a floor and some slots for testing
INSERT INTO floor (name) VALUES ('Ground Floor') ON CONFLICT DO NOTHING;
INSERT INTO slot (slot_number, vehicle_type, floor_id) VALUES ('A1', 'FOUR_WHEELER', 1) ON CONFLICT DO NOTHING;
INSERT INTO slot (slot_number, vehicle_type, floor_id) VALUES ('A2', 'FOUR_WHEELER', 1) ON CONFLICT DO NOTHING;
INSERT INTO slot (slot_number, vehicle_type, floor_id) VALUES ('B1', 'TWO_WHEELER', 1) ON CONFLICT DO NOTHING;

-- You can add more floors and slots here