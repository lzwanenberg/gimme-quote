
import { z } from "zod";

const EnvironmentVariablesSchema = z.object({
    QUOTE_SERVICE_HOST: z.string(),
    QUOTE_SERVICE_PORT: z.number().int().positive()
});

export default EnvironmentVariablesSchema;
