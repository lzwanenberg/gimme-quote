class InvalidConfigurationError extends Error {
    constructor(value: string) {
        super(value);
    }
}

export default InvalidConfigurationError;
