import EnvironmentVariables from '@/config/EnvironmentVariables';
import { QuoteServiceProvider } from '@/components/providers/QuoteServiceProvider';

type Props = {
    env: EnvironmentVariables;
    children?: React.ReactNode;
};

const AppContext = ({ env, children }: Props) => (
    <QuoteServiceProvider config={{
        host: env.QUOTE_SERVICE_HOST,
        port: env.QUOTE_SERVICE_PORT
    }}>
        {children}
    </QuoteServiceProvider>
)

export default AppContext;
