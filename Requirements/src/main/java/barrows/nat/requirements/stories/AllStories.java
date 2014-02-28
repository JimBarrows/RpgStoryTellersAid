package barrows.nat.requirements.stories;

import static java.util.Arrays.*;
import static org.jbehave.core.io.CodeLocations.*;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.io.UnderscoredCamelCaseResolver;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;

import barrows.nat.requirements.steps.Steps;

public class AllStories extends JUnitStories {

	private final CrossReference xref = new CrossReference();

	public AllStories() {
		Configuration configuration = new MostUsefulConfiguration()
        .useStoryLoader(new LoadFromURL());
		configuredEmbedder().embedderControls()
				.doGenerateViewAfterStories(true)
				.doIgnoreFailureInStories(false).doIgnoreFailureInView(false)
				.useThreads(2).useStoryTimeoutInSecs(60);
		// Uncomment to set meta filter, which can also be set via Ant or Maven
		// configuredEmbedder().useMetaFilters(Arrays.asList("+theme parametrisation"));
	}
	
	@Override
    protected List<String> storyPaths() {
        String codeLocation = codeLocationFromClass(this.getClass()).getFile();
        List<String>strings= new StoryFinder().findPaths(codeLocation, asList("**/add_new_game.story"),
                    asList(""), "file:"+codeLocation);
        return strings;
    }

	@Override
	public Configuration configuration() {
		Class<? extends Embeddable> embeddableClass = this.getClass();
		Properties viewResources = new Properties();
		viewResources.put("decorateNonHtml", "true");
		// Start from default ParameterConverters instance
		ParameterConverters parameterConverters = new ParameterConverters();
		// factory to allow parameter conversion and loading from external
		// resources (used by StoryParser too)
		ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(
				new LocalizedKeywords(),
				new LoadFromClasspath(embeddableClass), parameterConverters);
		// add custom converters
		parameterConverters.addConverters(new DateConverter(
				new SimpleDateFormat("yyyy-MM-dd")),
				new ExamplesTableConverter(examplesTableFactory));

		return new MostUsefulConfiguration()
				.useStoryControls(
						new StoryControls().doDryRun(false)
								.doSkipScenariosAfterFailure(false))
				.useStoryLoader(new LoadFromClasspath(embeddableClass))
				.useStoryParser(new RegexStoryParser(examplesTableFactory))
				.useStoryPathResolver(new UnderscoredCamelCaseResolver())
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withCodeLocation(
										CodeLocations
												.codeLocationFromClass(embeddableClass))
								.withDefaultFormats()
								.withPathResolver(new ResolveToPackagedName())
								.withViewResources(viewResources)
								.withFormats(CONSOLE, TXT, HTML, XML)
								.withFailureTrace(true)
								.withFailureTraceCompression(true)
								.withCrossReference(xref))
				.useParameterConverters(parameterConverters)
				// use '%' instead of '$' to identify parameters
				.useStepPatternParser(
						new RegexPrefixCapturingPatternParser("%"))
				.useStepMonitor(xref.getStepMonitor());
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new Steps());
	}

}
