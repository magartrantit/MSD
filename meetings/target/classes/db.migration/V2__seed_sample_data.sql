-- 1) MEETING
INSERT INTO meetings (
    id, team_id, type, title, description,
    scheduled_at, duration_minutes, status,
    facilitator_id, created_at, updated_at
) VALUES (
             '11111111-1111-1111-1111-111111111111',
             'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa',
             'STANDUP',
             'Daily Standup',
             'Standup meeting sample',
             '2026-01-14 09:30:00',
             15,
             'SCHEDULED',
             'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb',
             now(),
             now()
         );

-- 2) TOPICS
INSERT INTO topics (id, meeting_id, title, notes) VALUES
                                                      ('22222222-2222-2222-2222-222222222222', '11111111-1111-1111-1111-111111111111', 'Progress updates', 'Each member shares progress'),
                                                      ('33333333-3333-3333-3333-333333333333', '11111111-1111-1111-1111-111111111111', 'Blockers', 'Discuss blockers and solutions');

-- 3) PARTICIPANTS
INSERT INTO participants (id, meeting_id, user_id, attendance) VALUES
                                                                   ('44444444-4444-4444-4444-444444444444', '11111111-1111-1111-1111-111111111111', 'u-001', 'PRESENT'),
                                                                   ('55555555-5555-5555-5555-555555555555', '11111111-1111-1111-1111-111111111111', 'u-002', 'PRESENT'),
                                                                   ('66666666-6666-6666-6666-666666666666', '11111111-1111-1111-1111-111111111111', 'u-003', 'ABSENT');

-- 4) DECISIONS
INSERT INTO decisions (id, meeting_id, description) VALUES
    ('77777777-7777-7777-7777-777777777777', '11111111-1111-1111-1111-111111111111', 'We will prioritize bug fixes this sprint');

-- 5) ACTION ITEMS
INSERT INTO action_items (id, meeting_id, owner_id, title, due_date, status) VALUES
                                                                                 ('88888888-8888-8888-8888-888888888888', '11111111-1111-1111-1111-111111111111', 'u-001', 'Fix login bug', '2026-01-16', 'OPEN'),
                                                                                 ('99999999-9999-9999-9999-999999999999', '11111111-1111-1111-1111-111111111111', 'u-002', 'Prepare release notes', '2026-01-17', 'OPEN');