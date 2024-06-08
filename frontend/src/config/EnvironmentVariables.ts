import { TypeOf } from "zod";
import EnvironmentVariablesSchema from "./EnvironmentVariablesSchema";

type EnvironmentVariables = TypeOf<typeof EnvironmentVariablesSchema>;

export default EnvironmentVariables;
