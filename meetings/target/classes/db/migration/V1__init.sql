CREATE TABLE meetings (
                          id              VARCHAR(36) PRIMARY KEY,
                          team_id         VARCHAR(36) NOT NULL,
                          type            VARCHAR(30) NOT NULL,
                          title           VARCHAR(120) NOT NULL,
                          description     VARCHAR(500),
                          scheduled_at    TIMESTAMP NOT NULL,
                          duration_minutes INTEGER NOT NULL,
                          status          VARCHAR(30) NOT NULL,
                          facilitator_id  VARCHAR(36),
                          created_at      TIMESTAMP NOT NULL,
                          updated_at      TIMESTAMP NOT NULL
);

CREATE TABLE topics (
                        id          VARCHAR(36) PRIMARY KEY,
                        meeting_id  VARCHAR(36) NOT NULL,
                        title       VARCHAR(200) NOT NULL,
                        notes       VARCHAR(1000),
                        CONSTRAINT fk_topics_meeting
                            FOREIGN KEY (meeting_id) REFERENCES meetings(id) ON DELETE CASCADE
);

CREATE TABLE decisions (
                           id          VARCHAR(36) PRIMARY KEY,
                           meeting_id  VARCHAR(36) NOT NULL,
                           description VARCHAR(500) NOT NULL,
                           CONSTRAINT fk_decisions_meeting
                               FOREIGN KEY (meeting_id) REFERENCES meetings(id) ON DELETE CASCADE
);

CREATE TABLE action_items (
                              id          VARCHAR(36) PRIMARY KEY,
                              meeting_id  VARCHAR(36) NOT NULL,
                              owner_id    VARCHAR(36) NOT NULL,
                              title       VARCHAR(200) NOT NULL,
                              due_date    DATE NOT NULL,
                              status      VARCHAR(20) NOT NULL,
                              CONSTRAINT fk_action_items_meeting
                                  FOREIGN KEY (meeting_id) REFERENCES meetings(id) ON DELETE CASCADE
);

CREATE TABLE participants (
                              id          VARCHAR(36) PRIMARY KEY,
                              meeting_id  VARCHAR(36) NOT NULL,
                              user_id     VARCHAR(36) NOT NULL,
                              attendance  VARCHAR(20) NOT NULL,
                              CONSTRAINT fk_participants_meeting
                                  FOREIGN KEY (meeting_id) REFERENCES meetings(id) ON DELETE CASCADE
);

CREATE INDEX idx_topics_meeting_id       ON topics(meeting_id);
CREATE INDEX idx_decisions_meeting_id    ON decisions(meeting_id);
CREATE INDEX idx_action_items_meeting_id ON action_items(meeting_id);
CREATE INDEX idx_participants_meeting_id ON participants(meeting_id);

CREATE INDEX idx_meetings_team_id        ON meetings(team_id);
CREATE INDEX idx_meetings_status         ON meetings(status);
CREATE INDEX idx_action_items_status     ON action_items(status);
CREATE INDEX idx_action_items_due_date   ON action_items(due_date);
CREATE INDEX idx_participants_user_id    ON participants(user_id);
