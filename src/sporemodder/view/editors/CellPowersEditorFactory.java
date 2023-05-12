package sporemodder.view.editors;

import javafx.scene.Node;
import sporemodder.util.ProjectItem;

public class CellPowersEditorFactory implements EditorFactory {

	@Override
	public ItemEditor createInstance() {
		return new CellPowersEditor();
	}

	@Override
	public boolean isSupportedFile(ProjectItem item) {
		return !item.isFolder() && item.getName().endsWith(".powers_t");
	}
	
	@Override
	public Node getIcon(ProjectItem item) {
		return null;
	}
}