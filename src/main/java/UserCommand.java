

    public enum UserCommand {

        SIGNUP("1"),
        SIGNIN("2");

        private final String value;

        UserCommand(String value) {
            this.value = value;
        }

        public static UserCommand getCommandByValue(String value){
            // Перебираем все команды и ищем ту, которая соответствует заданному значению.
            for (UserCommand command : values()) {
                if (command.value.equals(value)) {
                    return command;
                }}
            throw new RuntimeException("Некорректный выбор");
        }}

