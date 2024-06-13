import EnvironmentVariablesSchema from "./EnvironmentVariablesSchema";
import ValidationResult from "./types/ValidationResult";

const validateEnvironmentVariables = (env: any): ValidationResult => {
    const config = EnvironmentVariablesSchema.safeParse(env);

    return config.success ?
        { success: true, data: config.data } :
        { success: false, error: JSON.stringify(config.error.format()) };
};

export default validateEnvironmentVariables;
