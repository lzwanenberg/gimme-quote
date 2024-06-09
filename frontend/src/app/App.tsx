import EnvironmentVariables from '@/config/EnvironmentVariables';
import { QuoteServiceProvider } from '@/components/providers/QuoteServiceProvider';
import './globals.css'

type Props = {
    env: EnvironmentVariables;
    children?: React.ReactNode;
};

const App = ({ env, children }: Props) => (
    <QuoteServiceProvider config={{
        host: env.QUOTE_SERVICE_HOST,
        port: env.QUOTE_SERVICE_PORT
    }}>
        <h1>Gimme Quote</h1>
        {children}
    </QuoteServiceProvider>
)

export default App;
