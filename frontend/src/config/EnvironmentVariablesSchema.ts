
import { z } from "zod";

const parseStringToInt = (value: unknown) => {
    if (typeof value != 'string') return value;
    const parsed = parseInt(value);

    if (Number.isNaN(parsed)) return value;
    return parsed;
}

const EnvironmentVariablesSchema = z.object({
    QUOTE_SERVICE_HOST: z.string(),
    QUOTE_SERVICE_PORT: z.preprocess(parseStringToInt, z.number().int().positive())
});



export default EnvironmentVariablesSchema;
