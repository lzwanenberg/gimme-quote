import EnvironmentVariables from "./EnvironmentVariables";
import InvalidConfigurationError from "./InvalidConfigurationError";
import validateEnvironmentVariables from "./validateEnvironmentVariables";

const loadEnvironmentVariables = (): EnvironmentVariables => {
    const result = validateEnvironmentVariables(process.env);

    if (!result.success)
        throw new InvalidConfigurationError(
            `Invalid environment variables configuration:\n${result.error}`);

    return result.data;
};

export default loadEnvironmentVariables;

