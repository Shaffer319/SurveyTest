# SurveyTest

Quick-and-dirty app to test inter-activity communication of surveys and responses.

Constants in MainActivity should be used for common string and integer flags.

Note that two versions of the survey and response are passed as extras in intents -- one plain JSON and one in which double quotes have been escaped.  Escaped JSON is used for pretty-printing but can be ignored if not needed.
