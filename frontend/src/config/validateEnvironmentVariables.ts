import EnvironmentVariablesSchema from "./EnvironmentVariablesSchema";
import EnvironmentVariables from "./EnvironmentVariables";

type ValidationSuccess = {
    success: true;
    result: EnvironmentVariables;
}

type ValidationFailed = {
    success: false;
    error: string;
}

type ValidationResult = ValidationSuccess | ValidationFailed;

const validateEnvironmentVariables = (env: any): ValidationResult => {
    const config = EnvironmentVariablesSchema.safeParse(env);

    return config.success ?
        { success: true, result: config.data } :
        { success: false, error: JSON.stringify(config.error.format()) };
};

export default validateEnvironmentVariables;