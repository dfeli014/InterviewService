rollback;
begin transaction;
/*Begin Dropping Tables*/
	/*Level 2 Tables*/
		drop table if exists interview;
	/*Level 1 Tables*/
		drop table if exists interview_feedback;
		drop table if exists associate_input;
	/*Level 0 Tables*/
		drop table if exists feedback_status;
		drop table if exists interview_format;
/*End Dropping Tables*/

/*Begin Creating Tables*/
	/*Level 0 Tables*/
		create table feedback_status (
			feedback_status_id               serial    not null unique,
			feedback_status_description      text      not null unique,
			constraint pk_feedback_status    primary key (feedback_status_id)
		);
		create table interview_format (
			interview_format_id              serial    not null unique,
			interview_format_description     text      not null unique,
			constraint pk_interview_format   primary key (interview_format_id)
		);
	/*Level 1 Tables*/
		create table interview_feedback (
			interview_feedback_id            serial    not null unique,
			feedback_requested               timestamp not null,
			feedback                         text      not null,
			feedback_received                timestamp not null,
			feedback_delivered               timestamp,
			feedback_status                  integer   not null,
			constraint pk_interview_feedback primary key (interview_feedback_id),
			constraint fk_feedback_status    foreign key (feedback_status) references feedback_status (feedback_status_id)
		);
		create table associate_input (
			associate_input_id               serial    not null unique,
			received_notifications           boolean   not null,
			day_notice                       boolean   not null,
			description_provided             boolean   not null,
			interview_format                 integer   not null,
			proposed_format                  integer   not null,
			constraint pk_associate_input    primary key (associate_input_id),
			constraint fk_interview_format   foreign key (interview_format) references interview_format (interview_format_id),
			constraint fk_proposed_format    foreign key (proposed_format) references interview_format (interview_format_id)
		);
	/*Level 2 Tables*/
		create table interview (
			interview_id                     serial    not null unique,
			manager_id                       integer   not null,
			associate_id                     integer   not null,
			scheduled                        timestamp not null,
			location                         text      not null,
			reviewed                         timestamp,
			interview_feedback               integer,
			associate_input                  integer,
			constraint interview_id          primary key (interview_id)
		);
/*End Creating Tables*/
	
commit; 